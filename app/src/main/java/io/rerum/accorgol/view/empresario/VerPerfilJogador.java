package io.rerum.accorgol.view.empresario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.rerum.accorgol.R;
import io.rerum.accorgol.view.jogador.PesquisarEmpresario;

/**
 * Created by osman on 31/10/2017.
 */

public class VerPerfilJogador extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfiljogador);
    }

    public void voltarTela(View view) {
        this.finish();
    }

}
