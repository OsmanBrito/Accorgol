package io.rerum.accorgol.view.jogador.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import io.rerum.accorgol.R;

/**
 * Created by osman on 14/11/2017.
 */

public class CarreiraFragment extends Fragment {

    private Spinner spinner;
    private EditText golSofridos;
    private EditText ateDataNoClubeateDataNoClube;
    private CheckBox clubeAtual;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.carreira_layout, container, false);
        spinner = (Spinner) view.findViewById(R.id.posicaoClube);
        golSofridos = (EditText) view.findViewById(R.id.golsSofridos);
        ateDataNoClubeateDataNoClube = (EditText) view.findViewById(R.id.ateClube);
        clubeAtual = (CheckBox) view.findViewById(R.id.clubeAtual);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("CarreiraFragment", spinner.getItemAtPosition(i).toString());
                String posicao = spinner.getItemAtPosition(i).toString();
                if (posicao.equals("Goleiro") || posicao.equals("Zagueiro")) {
                    golSofridos.setEnabled(true);
                } else {
                    golSofridos.setEnabled(false);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        clubeAtual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clubeAtual.isChecked()) {
                    ateDataNoClubeateDataNoClube.setEnabled(false);
                } else { ateDataNoClubeateDataNoClube.setEnabled(true); }
            }
        });
        return view;
    }



}
