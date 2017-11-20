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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnPausedListener;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import io.rerum.accorgol.R;
import io.rerum.accorgol.dao.UsuarioDAO;
import io.rerum.accorgol.view.MainActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by osman on 19/11/2017.
 */

public class SemVideoFragment extends Fragment {

    private BootstrapButton addVideo;
    private static final int GALLERY_INTENT = 2;
    static final int REQUEST_VIDEO_CAPTURE = 1;
    private Context ctx;
    private StorageReference mStorage;
    private ProgressDialog progressDoalog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sem_video_jogador, container, false);
        addVideo = (BootstrapButton) view.findViewById(R.id.addVideo);
        ctx = view.getContext();
        mStorage = FirebaseStorage.getInstance().getReference();
        addVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(view.getContext())
                        .setTitle("Escolha de Vídeo")
                        .setMessage("Escolha uma opção para adicionar um vídeo.")
                        .setPositiveButton("Galeria", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
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

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) || requestCode == REQUEST_VIDEO_CAPTURE){

            Uri uri = data.getData();
            Log.e("osman - ", String.valueOf(uri));
            StorageReference filepath = mStorage.child("Videos").child(String.valueOf(new UsuarioDAO(ctx).getIdUsuario()));
            progressDoalog = new ProgressDialog(ctx);
            progressDoalog.setMax(100);
            progressDoalog.setMessage("Upload do vídeo");
            progressDoalog.setTitle("Fazendo o upload do vídeo");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDoalog.show();

            filepath.putFile(uri).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
//                    Toast.makeText(ctx, "Download: "+progress+"% ", Toast.LENGTH_LONG).show();
                    System.out.println("Upload is " + progress + "% done");
                    progressDoalog.setProgress((int)progress);
                    if (progress == 100) {
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



}
