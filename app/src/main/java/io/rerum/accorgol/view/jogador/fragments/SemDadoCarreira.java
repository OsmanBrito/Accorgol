package io.rerum.accorgol.view.jogador.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beardedhen.androidbootstrap.BootstrapButton;

import io.rerum.accorgol.R;

/**
 * Created by osman on 14/11/2017.
 */

public class SemDadoCarreira extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.sem_carreira_fragment,
                container, false);
        BootstrapButton button = (BootstrapButton) view.findViewById(R.id.addClube);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                android.app.FragmentManager manager = getFragmentManager();
                AddCarreiraFragment cc = new AddCarreiraFragment();
                manager.beginTransaction().replace(R.id.contentContainer, cc, cc.getTag()).commit();
            }
        });
        return view;

    }
}
