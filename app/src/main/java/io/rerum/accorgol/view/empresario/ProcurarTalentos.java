package io.rerum.accorgol.view.empresario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapEditText;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.EmpresarioService;
import io.rerum.accorgol.model.Jogador;
import io.rerum.accorgol.view.jogador.Jogador_Perfil;

/**
 * Created by osman on 31/10/2017.
 */

public class ProcurarTalentos extends AppCompatActivity {

    private Spinner peDominante;
    private Spinner posicao;
    private BootstrapEditText ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscatalentos);

        peDominante = (Spinner) findViewById(R.id.peDominanteBuscar);
        posicao = (Spinner) findViewById(R.id.posicaoBuscar);
        ano = (BootstrapEditText) findViewById(R.id.buscarANo);

    }

    public void BuscarTalentos(View view) {
        Intent myIntent = new Intent(this, ResultadoBuscaDeTalentos.class);
        String buscarPosicao = this.posicao.getSelectedItem().toString();
        String buscarPe = this.peDominante.getSelectedItem().toString();
        Jogador jogador = new Jogador(buscarPosicao, buscarPe, ano.getText().toString());
        myIntent.putExtra("posicao", buscarPosicao);
        myIntent.putExtra("pe", buscarPe);
        myIntent.putExtra("ano", ano.getText().toString());
        startActivity(myIntent);
    }
}
