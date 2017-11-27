package io.rerum.accorgol.view.jogador.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.dao.UsuarioDAO;

import static android.app.Activity.RESULT_OK;

/**
 * Created by osman on 19/11/2017.
 */

public class JogadorPerfilFragment extends Fragment {
    private ImageButton fotoButton;
    private ImageView fotoPerfil;
    private static final int GALLERY_INTENT = 2;
    private Context ctx;
    private StorageReference mStorage;
    private ProgressDialog progressDoalog;
    private DatabaseReference mDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfil_jogador_fragment, container, false);
        ctx = view.getContext();
        mStorage = FirebaseStorage.getInstance().getReference();
        SemVideoFragment semVideoFragment = new SemVideoFragment();
        ComVideoFragment comVideoFragment = new ComVideoFragment();
        android.app.FragmentManager manager = getFragmentManager();
        if (new UsuarioDAO(view.getContext()).hasVideo() == 1 || new FirebaseHelper().recuperar(view.getContext(), "VideoPerfil").equals("Tem")){
            manager.beginTransaction().replace(R.id.contentContainervideoJogador, comVideoFragment, comVideoFragment.getTag()).commit();
        } else {
            manager.beginTransaction().replace(R.id.contentContainervideoJogador, semVideoFragment, semVideoFragment.getTag()).commit();
        }
        fotoButton = (ImageButton) view.findViewById(R.id.addFotoPerfil);
        fotoPerfil = (ImageView) view.findViewById(R.id.fotoPerfilJogador);
        fotoButton.setOnClickListener(new View.OnClickListener() {
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
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == GALLERY_INTENT && resultCode == RESULT_OK) || requestCode == 2) {

            final Uri uri = data.getData();
            final StorageReference filepath = mStorage.child("Fotos/Perfil").child(String.valueOf(new FirebaseHelper().recuperar(ctx, String.valueOf(R.string.id_Usuario))));
            progressDoalog = new ProgressDialog(ctx);
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Upload da fotoButton");
            progressDoalog.setTitle("Fazendo o upload da fotoButton");
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
                        UsuarioDAO dao = new UsuarioDAO(ctx);
                        dao.attFotoUsuario(true);
                        progressDoalog.dismiss();
                        FirebaseHelper firebaseHelper = new FirebaseHelper();
                        Uri testeUri = taskSnapshot.getMetadata().getDownloadUrl();
                        firebaseHelper.armazenar(ctx, String.valueOf(testeUri), String.valueOf(R.string.foto_perfil));
                        Picasso.with(ctx).load(testeUri).into(fotoPerfil);
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

    @Override
    public void onStart(){
//        mDatabase = FirebaseDatabase.getInstance().getReference();
        try {
            Picasso.with(ctx).load(new FirebaseHelper().recuperar(ctx, String.valueOf(R.string.foto_perfil)).toString()).into(fotoPerfil);
        }catch (Exception e){
            Log.e("USUARIO CATHC!!! ", String.valueOf(e));
            InputStream istr = null;
            try {
                istr = ctx.getAssets().open("ic_laucher.png");
                this.fotoPerfil.setImageDrawable(Drawable.createFromStream(istr, null));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            //set drawable from stream
        }
        super.onStart();
    }
}
