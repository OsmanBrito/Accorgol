package io.rerum.accorgol.view.jogador.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.rerum.accorgol.R;

/**
 * Created by osman on 14/11/2017.
 */

public class PerfilFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.perfiljogador, container, false);
    }
}
