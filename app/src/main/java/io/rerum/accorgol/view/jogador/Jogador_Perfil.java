package io.rerum.accorgol.view.jogador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import io.rerum.accorgol.R;

/**
 * Created by osman on 17/10/2017.
 */

public class Jogador_Perfil extends AppCompatActivity{

    private String email;
    private String senha;
    private String nome;
    private String anoNascimento;
    private String pedominante;
    private String rg;
    private String posicao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogadorperfil);

        Intent intent = getIntent();
        this.email = intent.getStringExtra("email");
        this.senha = intent.getStringExtra("senha");
        this.nome = intent.getStringExtra("nome");
        this.anoNascimento = intent.getStringExtra("anoNascimento");
        this.pedominante = intent.getStringExtra("pedominante");
        this.rg = intent.getStringExtra("rg");
        this.posicao = intent.getStringExtra("posicao");


    }
}
