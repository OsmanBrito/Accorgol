package io.rerum.accorgol.view.jogador;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.ConquistaService;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.model.Conquista;
import io.rerum.accorgol.view.jogador.fragments.ComConquistaFragment;

/**
 * Created by osman on 30/11/2017.
 */

public class EditarConquista extends AppCompatActivity {

    private ImageButton buttonImage;
    private ImageView fotoConquista;
    private BootstrapButton addConquista;
    private TextView nomeConquista;
    private TextView anoConquista;
    private TextView nomeClubeConquista;
    private static final int GALLERY_INTENT = 2;
    private ProgressDialog progressDoalog;
    private Context ctx;
    private StorageReference mStorage;
    private Uri uriConquista;
    private String idConquista;
    //servira para ver se teve nova foto
    private Boolean flag = false;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_conquista_fragment);

        Intent intent = getIntent();
        idConquista = intent.getStringExtra("idConquista");
        Log.d("USUARIOOOOO VsssER O ID DA CONQUIATA =", idConquista);

        ctx = getApplicationContext();
        mStorage = FirebaseStorage.getInstance().getReference();
        addConquista = (BootstrapButton) findViewById(R.id.addItemConquista);
        fotoConquista = (ImageView) findViewById(R.id.fotoConquista);
        nomeClubeConquista = (TextView) findViewById(R.id.nomeClubeConquista);
        nomeConquista = (TextView) findViewById(R.id.nomeConquista);
        anoConquista = (TextView) findViewById(R.id.anoConquista);
        buttonImage = (ImageButton) findViewById(R.id.addFotoConquista);

        buscarConquista();

        buttonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Escolha de Foto")
                        .setMessage("Escolha uma opção para adicionar uma fotoButton.")
                        .setPositiveButton("Galeria", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                startActivityForResult(intent, GALLERY_INTENT);
                                flag = true;
                            }
                        })
                        .setNeutralButton("Capturar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dispatchTakeVideoIntent();
                            }
                        })
                        .setIcon(R.mipmap.ic_launcher)
                        .setNegativeButton("Cancel", null)
                        .show();
                view.setVisibility(View.GONE);
            }
        });
        addConquista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final StorageReference filepath = mStorage.child("Fotos/Conquista/" + new FirebaseHelper().recuperar(ctx, String.valueOf(R.string.id_Usuario))).child(String.valueOf(getIdFotoConquista()));
                if (flag) {
                    progressDoalog = new ProgressDialog(ctx);
                    progressDoalog.setMax(100);
                    progressDoalog.setMessage("Upload da foto");
                    progressDoalog.setTitle("Fazendo o upload da foto");
                    progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDoalog.show();

                    filepath.putFile(uriConquista).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                    Toast.makeText(ctx, "Download: "+progress+"% ", Toast.LENGTH_LONG).show();
                            System.out.println("Upload is " + progress + "% done");
                            progressDoalog.setProgress((int) progress);
                            progressDoalog.setCancelable(false);
                            if (progress == 100) {
                                FirebaseHelper firebaseHelper = new FirebaseHelper();
                                Uri testeUri = taskSnapshot.getMetadata().getDownloadUrl();
                                firebaseHelper.armazenar(ctx, String.valueOf(testeUri), String.valueOf(R.string.foto_conquista));
                                Picasso.with(ctx).load(testeUri).into(fotoConquista);
                            }
                        }
                    }).addOnPausedListener(new OnPausedListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onPaused(UploadTask.TaskSnapshot taskSnapshot) {
                            System.out.println("Upload is paused");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                        }
                    }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Handle successful uploads on complete
                            Uri downloadUrl = taskSnapshot.getMetadata().getDownloadUrl();

                        }
                    });
                }

                final Conquista conquista = new Conquista( nomeConquista.getText().toString(),
                        anoConquista.getText().toString(),
                        new FirebaseHelper().recuperar(ctx, String.valueOf(R.string.foto_conquista)),
                        nomeClubeConquista.getText().toString());

                conquista.setIdConquista(idConquista);

                String id = new FirebaseHelper().recuperar(ctx, String.valueOf(R.string.id_Usuario));
                mDatabase = FirebaseDatabase.getInstance().getReference();
                final FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Jogadores/"+id+"/Conquistas/"+idConquista);
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        myRef.setValue(conquista);
                        finish();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e("COEEEE ERRROOOO", databaseError.toString());
                    }
                });
            }
        });
    }

    private void buscarConquista() {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("/Jogadores/"+new FirebaseHelper().recuperar(this, String.valueOf(R.string.id_Usuario)) + "/Conquistas/"+idConquista);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("COEEEE", "buscar empresario "+dataSnapshot);
                Picasso.with(ctx).load(Uri.parse(dataSnapshot.child("urifoto").getValue(String.class))).into(fotoConquista);
                nomeConquista.setText(dataSnapshot.child("nomeConquista").getValue(String.class));
                nomeClubeConquista.setText(dataSnapshot.child("nomeClube").getValue(String.class));
                anoConquista.setText(dataSnapshot.child("ano").getValue(String.class));

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == GALLERY_INTENT && resultCode == RESULT_OK) || requestCode == 2) {

            final Uri uri = data.getData();
            //new FirebaseHelper().armazenar(ctx, uri.toString(), String.valueOf(R.string.uri_foto));
            uriConquista = uri;
            fotoConquista.setImageURI(uri);
        }
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
        startActivityForResult(takeVideoIntent, 2);
//        }
    }

    private int getIdFotoConquista(){
        FirebaseHelper firebaseHelper = new FirebaseHelper();
        String id = firebaseHelper.recuperar(ctx, String.valueOf(R.string.id_Foto_Conquista));
        firebaseHelper.armazenar(ctx, String.valueOf(Integer.parseInt(id) + 1), String.valueOf(R.string.id_Foto_Conquista));
        int idFoto = Integer.parseInt(id);
        Log.e("USUARIOOO ver o idFoto = ", String.valueOf(idFoto));
        return idFoto + 1;
    }
}