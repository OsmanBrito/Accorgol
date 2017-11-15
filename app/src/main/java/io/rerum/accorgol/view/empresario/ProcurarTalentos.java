package io.rerum.accorgol.view.empresario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import io.rerum.accorgol.R;
import io.rerum.accorgol.view.jogador.Jogador_Perfil;

/**
 * Created by osman on 31/10/2017.
 */

public class ProcurarTalentos extends AppCompatActivity {

    private Spinner peDominante;
    private Spinner posicao;
    private Spinner altura;
    private Spinner estado;
    private EditText cidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscatalentos);

        peDominante = (Spinner) findViewById(R.id.peDominanteBuscar);
        posicao = (Spinner) findViewById(R.id.posicaoBuscar);
        altura = (Spinner) findViewById(R.id.alturaBuscar);
        estado = (Spinner) findViewById(R.id.estadoBuscar);
        cidade = (EditText) findViewById(R.id.cidadeBuscar);

    }

    public void BuscarTalentos(View view) {
        Intent myIntent = new Intent(this, ResultadoBuscaDeTalentos.class);
        myIntent.putExtra("peDominante", this.peDominante.getSelectedItem().toString());
        myIntent.putExtra("posicao", this.posicao.getSelectedItem().toString());
        myIntent.putExtra("altura", this.altura.getSelectedItem().toString());
        myIntent.putExtra("estado", this.estado.getSelectedItem().toString());
        myIntent.putExtra("cidade", this.cidade.getText().toString());
        startActivity(myIntent);
    }
}
