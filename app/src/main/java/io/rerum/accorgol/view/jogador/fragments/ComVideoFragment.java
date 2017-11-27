package io.rerum.accorgol.view.jogador.fragments;

import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;

/**
 * Created by osman on 20/11/2017.
 */

public class ComVideoFragment extends Fragment {
    private VideoView video;
    private ImageButton play;
    private Context ctx;
    private static final String TAG = "ComVideoFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.com_video_fragment, container, false);
        ctx = view.getContext();
        video = (VideoView) view.findViewById(R.id.videoJogador);
        play = (ImageButton) view.findViewById(R.id.playVideoPerfil);
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference mStorage = storage.getReference();
        StorageReference storageReference = mStorage.child("Videos/"+new FirebaseHelper().recuperar(view.getContext(), String.valueOf(R.string.id_Usuario)));
        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                video.setVideoURI(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video.start();
                view.setVisibility(View.GONE);
            }
        });
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

            }
        });
        return view;
    }
}
