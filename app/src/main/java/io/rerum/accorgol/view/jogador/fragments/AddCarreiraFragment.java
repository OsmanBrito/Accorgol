package io.rerum.accorgol.view.jogador.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapButton;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.CarreiraService;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.model.Carreira;

/**
 * Created by osman on 14/11/2017.
 */

public class AddCarreiraFragment extends Fragment {

    private Spinner posicaoNoClube;
    private EditText golSofridos;
    private EditText nomeClube;
    private EditText deDataNoClube;
    private EditText qndtPartidasNoClube;
    private EditText qntdGols;
    private EditText qntdAssistencias;
    private EditText ateDataNoClube;
    private CheckBox clubeAtual;
    private BootstrapButton bootstrapButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_carreira_fragment, container, false);
        posicaoNoClube = (Spinner) view.findViewById(R.id.posicaoClube);
        nomeClube = (EditText) view.findViewById(R.id.nomeClubeAddCarreira);
        deDataNoClube = (EditText) view.findViewById(R.id.inicioClube);
        ateDataNoClube = (EditText) view.findViewById(R.id.ateClube);
        qndtPartidasNoClube = (EditText) view.findViewById(R.id.quantidadePartidasClube);
        qntdGols = (EditText) view.findViewById(R.id.golsClube);
        qntdAssistencias = (EditText) view.findViewById(R.id.assistenciaClube);
        golSofridos = (EditText) view.findViewById(R.id.golsSofridos);
        bootstrapButton = (BootstrapButton) view.findViewById(R.id.addCarreira);
        clubeAtual = (CheckBox) view.findViewById(R.id.clubeAtual);
        posicaoNoClube.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("AddCarreiraFragment", posicaoNoClube.getItemAtPosition(i).toString());
                String posicao = posicaoNoClube.getItemAtPosition(i).toString();
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
                    ateDataNoClube.setEnabled(false);
                } else { ateDataNoClube.setEnabled(true); }
            }
        });
        bootstrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = nomeClube.getText().toString();
                String de = deDataNoClube.getText().toString();
                String posicao = posicaoNoClube.getSelectedItem().toString();
                int partidas = Integer.parseInt(qndtPartidasNoClube.getText().toString());
                int gols = Integer.parseInt(qntdGols.getText().toString());
                int assistencias = Integer.parseInt(qntdAssistencias.getText().toString());
                int  sofridos = Integer.parseInt(golSofridos.getText().toString());
                //se o jogador for goleiro ou zagueiro e esta atuando no clube em questão é instanciado o construtor dele.
                if (posicao.equals("Goleiro") || posicao.equals("Zagueiro") && clubeAtual.isChecked()) {
                    Carreira carreira = new Carreira(nome, de, true, posicao, partidas, gols, assistencias, sofridos);
                    String id = new FirebaseHelper().recuperar(view.getContext(), String.valueOf(R.string.id_Usuario));
                    new CarreiraService().addCarreira(carreira, "Jogadores/"+ id + "/Carreira/", view.getContext());
                    android.app.FragmentManager manager = getFragmentManager();
                    ComCarreiraFragment cc = new ComCarreiraFragment();
                    manager.beginTransaction().replace(R.id.contentContainer, cc, cc.getTag()).commit();
                }
            }
        });
        return view;
    }



}
