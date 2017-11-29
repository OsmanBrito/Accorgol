package io.rerum.accorgol.view.jogador.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.VideoView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import io.rerum.accorgol.R;

/**
 * Created by osman on 29/11/2017.
 */

public class VisualizarSemVideoFragment extends Fragment {
    private BootstrapButton addVideo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.sem_video_jogador, container, false);
        addVideo = (BootstrapButton) view.findViewById(R.id.addVideo);
        addVideo.setVisibility(View.INVISIBLE);
        return view;
    }
}
