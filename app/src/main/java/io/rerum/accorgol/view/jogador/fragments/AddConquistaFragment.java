package io.rerum.accorgol.view.jogador.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.ConquistaService;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.dao.UsuarioDAO;
import io.rerum.accorgol.model.Conquista;

import static android.app.Activity.RESULT_OK;

/**
 * Created by osman on 22/11/2017.
 */

public class AddConquistaFragment extends Fragment {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_conquista_fragment, container, false);
        ctx = view.getContext();
        mStorage = FirebaseStorage.getInstance().getReference();
        addConquista = (BootstrapButton) view.findViewById(R.id.addItemConquista);
        fotoConquista = (ImageView) view.findViewById(R.id.fotoConquista);
        nomeClubeConquista = (TextView) view.findViewById(R.id.nomeClubeConquista);
        nomeConquista = (TextView) view.findViewById(R.id.nomeConquista);
        anoConquista = (TextView) view.findViewById(R.id.anoConquista);
        buttonImage = (ImageButton) view.findViewById(R.id.addFotoConquista);
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
                Conquista conquista = new Conquista(nomeConquista.getText().toString(),
                                                    nomeClubeConquista.getText().toString(),
                                                    anoConquista.getText().toString(),
                                                    new FirebaseHelper().recuperar(ctx, "fotoConquista"));
                ConquistaService conquistaService = new ConquistaService();
                String id = new FirebaseHelper().recuperar(ctx, "IDUsuario");
                conquistaService.addConquista(conquista, "Jogadores/"+ id + "/Conquistas", ctx);
                android.app.FragmentManager manager = getFragmentManager();
                ComConquistaFragment cc = new ComConquistaFragment();
                manager.beginTransaction().replace(R.id.contentContainer, cc, cc.getTag()).commit();
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == GALLERY_INTENT && resultCode == RESULT_OK) || requestCode == 2) {

            final Uri uri = data.getData();
            final StorageReference filepath = mStorage.child("Fotos/Conquista").child(String.valueOf(new FirebaseHelper().recuperar(ctx, "IDUsuario"))+"-"+data.getScheme());
            progressDoalog = new ProgressDialog(ctx);
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Upload da foto");
            progressDoalog.setTitle("Fazendo o upload da foto");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDoalog.show();

            filepath.putFile(uri).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
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
                        progressDoalog.dismiss();
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
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
        startActivityForResult(takeVideoIntent, 2);
//        }
    }

}
