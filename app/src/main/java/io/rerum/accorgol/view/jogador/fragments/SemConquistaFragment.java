package io.rerum.accorgol.view.jogador.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.beardedhen.androidbootstrap.BootstrapButton;

import io.rerum.accorgol.R;

/**
 * Created by osman on 22/11/2017.
 */

public class SemConquistaFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sem_conquista_fragment, container, false);
        BootstrapButton bootstrapButton = (BootstrapButton) view.findViewById(R.id.addConquista);
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
