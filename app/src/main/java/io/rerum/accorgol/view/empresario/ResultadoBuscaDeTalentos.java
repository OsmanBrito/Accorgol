package io.rerum.accorgol.view.empresario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import io.rerum.accorgol.R;
import io.rerum.accorgol.view.jogador.RVAdapter;

/**
 * Created by osman on 31/10/2017.
 */

public class ResultadoBuscaDeTalentos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultadobuscajogador);

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);
        //quando eu sei que o tamanho do rv não vai mudar
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        //TODO pegar os dados do jogador com caracteristicas da busca por parametro
//        RVAdapter adapter = new RVAdapter();
//        rv.setAdapter(adapter);
    }

    public void verPerfilJogador(View view) {
        Intent myIntent = new Intent(this, VerPerfilJogador.class);
        startActivity(myIntent);
    }

    public void voltarTela(View view) {
        this.finish();
    }
}
