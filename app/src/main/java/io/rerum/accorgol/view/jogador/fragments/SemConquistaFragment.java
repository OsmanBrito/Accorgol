package io.rerum.accorgol.view.jogador.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;

/**
 * Created by osman on 22/11/2017.
 */

public class SemConquistaFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sem_conquista_fragment, container, false);
        BootstrapButton bootstrapButton = (BootstrapButton) view.findViewById(R.id.addConquista);
        //ira armazenar um 'id' das fotos das conquistas do jogador, assim quando for adicionado
        //ira buscar qual o id e depois ira incrementar e armazenar novamente.
        new FirebaseHelper().armazenar(view.getContext(), "0", String.valueOf(R.string.id_Foto_Conquista));
        Log.e("SemConquistaFragment", "Entrou aqui");
        bootstrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager manager = getFragmentManager();
                AddConquistaFragment addConquistaFragment = new AddConquistaFragment();
                manager.beginTransaction().replace(R.id.contentContainer, addConquistaFragment, addConquistaFragment.getTag()).commit();
            }
        });
        return view;
    }

}
