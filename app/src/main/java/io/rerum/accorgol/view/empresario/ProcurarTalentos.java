package io.rerum.accorgol.view.empresario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.rerum.accorgol.R;
import io.rerum.accorgol.view.jogador.Jogador_Perfil;

/**
 * Created by osman on 31/10/2017.
 */

public class ProcurarTalentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscatalentos);
    }

    public void BuscarTalentos(View view) {
        Intent myIntent = new Intent(this, ResultadoBuscaDeTalentos.class);
        startActivity(myIntent);
    }
}
