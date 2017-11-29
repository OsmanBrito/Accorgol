package io.rerum.accorgol.view.jogador.fragments;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;

/**
 * Created by osman on 28/11/2017.
 */

public class VisualizarVideoFragment extends Fragment{

    private VideoView videoJogador;
    private ImageButton playVideoPerfil;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.com_video_fragment, container, false);
        videoJogador = (VideoView) view.findViewById(R.id.videoJogador);
        playVideoPerfil = (ImageButton) view.findViewById(R.id.playVideoPerfil);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String id = new FirebaseHelper().recuperar(view.getContext(), String.valueOf(R.string.id_jogador_que_sera_visualizado));
        DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Jogadores/"+id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnaphot) {
                if (dataSnaphot.child("urivideo").getValue(String.class) != null) {
                    videoJogador.setVideoURI(Uri.parse(dataSnaphot.child("urivideo").getValue(String.class)));
                    playVideoPerfil.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            videoJogador.start();
                            view.setVisibility(View.GONE);
                        }
                    });
                } else {
                    android.app.FragmentManager manager = getFragmentManager();
                    VisualizarSemVideoFragment sv = new VisualizarSemVideoFragment();

                    manager.beginTransaction().replace(R.id.contentVideoJogador, sv, sv.getTag()).commit();
                }
//                Picasso.with(view.getContext()).load(dataSnapshot.child("urifoto").getValue(String.class)).into(foto);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

}
