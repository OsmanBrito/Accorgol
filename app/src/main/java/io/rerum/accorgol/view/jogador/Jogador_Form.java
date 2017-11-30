package io.rerum.accorgol.view.jogador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.firebase.client.Firebase;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.JogadorService;
import io.rerum.accorgol.model.Jogador;

/**
 * Created by osman on 13/08/2017.
 */

public class Jogador_Form extends AppCompatActivity{

    private Firebase mRootRef;

    private boolean flag;

    private String TAG = "LOG AQUI ACCORGOL";

    private EditText nameJogador;
    private EditText anoNascimento;
    private RadioButton peDominante;
    private RadioGroup radioGroup;
    private EditText RG;
    private Spinner posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogador_form);
        Firebase.setAndroidContext(this);

        nameJogador = (EditText) findViewById(R.id.cadastroNomeJogador);
        anoNascimento = (EditText) findViewById(R.id.cadastroAnoJogador);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        RG = (EditText) findViewById(R.id.cadastroRGJogador);
        posicao = (Spinner) findViewById(R.id.posicao);
    }

    public void JogadorCadastrado(View view) {

        int selectedId = radioGroup.getCheckedRadioButtonId();

        Log.e("COEEE VER O RADIO", String.valueOf(selectedId));

        // find the radiobutton by returned id
        peDominante = (RadioButton) findViewById(selectedId);

        String nome = this.nameJogador.getText().toString();
        String posicao = this.posicao.getSelectedItem().toString();
        String anoNascimento = this.anoNascimento.getText().toString();
        String RG = this.RG.getText().toString();
        String peDominante = this.peDominante.getText().toString();

        Intent myIntent = new Intent(this, Jogador_Form_Segund.class);
        myIntent.putExtra("nome", nome);
        myIntent.putExtra("posicao", posicao);
        myIntent.putExtra("anoNascimento", anoNascimento);
        myIntent.putExtra("RG", RG);
        myIntent.putExtra("peDominante", peDominante);
        startActivity(myIntent);
    }
}
