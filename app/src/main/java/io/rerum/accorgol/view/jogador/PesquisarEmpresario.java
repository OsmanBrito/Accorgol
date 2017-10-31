package io.rerum.accorgol.view.jogador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.rerum.accorgol.R;
import io.rerum.accorgol.view.empresario.BuscarEmpresarios;

/**
 * Created by osman on 31/10/2017.
 */

public class PesquisarEmpresario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pesquisarempresario);
    }

    public void buscarEmpresarios(View view) {
        Intent myIntent = new Intent(this, BuscarEmpresarios.class);
        startActivity(myIntent);
    }
}
