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
 * Created by osman on 30/11/2017.
 */

public class Jogador_Form_Segund extends AppCompatActivity {

    private Firebase mRootRef;

    private boolean flag;

    private String TAG = "LOG AQUI ACCORGOL";

    private EditText altura;
    private Spinner estado;
    private EditText cidade;
    private EditText celular;
    private String nomeCompleto;
    private String posicao;
    private String anoNascimento;
    private String rg;
    private String peDominante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogador_form_segunda_etapa);

        Intent intent = getIntent();
        nomeCompleto = intent.getStringExtra("nome");
        posicao = intent.getStringExtra("posicao");
        anoNascimento = intent.getStringExtra("anoNascimento");
        rg = intent.getStringExtra("RG");
        peDominante = intent.getStringExtra("peDominante");

        altura = (EditText) findViewById(R.id.cadastroAlturaJogador);
        estado = (Spinner) findViewById(R.id.cadastroEstadoJogador);
        cidade = (EditText) findViewById(R.id.cadastroCidadeJogador);
        celular = (EditText) findViewById(R.id.cadastroCelJogador);

    }

    public void CadastrarJogador(View view) {


        String altura = this.altura.getText().toString();
        String cidade = this.cidade.getText().toString();
        String celular = this.celular.getText().toString();
        String estado = this.estado.getSelectedItem().toString();
        Jogador jogador = new Jogador(nomeCompleto, posicao, peDominante, altura, rg, anoNascimento, cidade, estado, celular);
        new JogadorService().cadastrarJogador(jogador, getApplicationContext());
        Intent myIntent = new Intent(this, Jogador_Perfil.class);
        startActivity(myIntent);

    }
}
