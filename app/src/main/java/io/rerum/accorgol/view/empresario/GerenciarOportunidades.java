package io.rerum.accorgol.view.empresario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.view.empresario.fragments.ComOportunidadeFragment;
import io.rerum.accorgol.view.empresario.fragments.SemOportunidadeFragment;
import io.rerum.accorgol.view.jogador.fragments.JogadorPerfilFragment;

/**
 * Created by osman on 31/10/2017.
 */

public class GerenciarOportunidades extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gerenciaroportunidades);
        android.app.FragmentManager manager = getFragmentManager();
        if (new FirebaseHelper().recuperar(getApplicationContext(), String.valueOf(R.string.tem_oportunidade)).equals("Tem")){
            ComOportunidadeFragment jo = new ComOportunidadeFragment();
            manager.beginTransaction().replace(R.id.contentOportunidades, jo, jo.getTag()).commit();
        } else {
            SemOportunidadeFragment so = new SemOportunidadeFragment();
            manager.beginTransaction().replace(R.id.contentOportunidades, so, so.getTag()).commit();
        }

    }

    public void addOportunidade(View view) {
        Intent myIntent = new Intent(this, AddOportunidade.class);
        startActivity(myIntent);
    }

    public void voltarTela(View view) {
        this.finish();
    }
}
