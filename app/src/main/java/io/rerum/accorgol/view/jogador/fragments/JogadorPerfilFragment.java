package io.rerum.accorgol.view.jogador.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.rerum.accorgol.R;

/**
 * Created by osman on 19/11/2017.
 */

public class JogadorPerfilFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfil_jogador_fragment, container, false);
        SemVideoFragment semVideoFragment = new SemVideoFragment();
        android.app.FragmentManager manager = getFragmentManager();
        manager.beginTransaction().replace(R.id.contentContainervideoJogador, semVideoFragment, semVideoFragment.getTag()).commit();

        return view;
    }

}
