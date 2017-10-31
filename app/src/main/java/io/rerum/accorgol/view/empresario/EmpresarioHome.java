package io.rerum.accorgol.view.empresario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.rerum.accorgol.R;

/**
 * Created by osman on 26/09/2017.
 */

public class EmpresarioHome extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empresariohome);

    }

    public void empresarioPerfil(View view) {
        Intent intent  = new Intent(this, Empresario_Perfil.class);
        startActivity(intent);
    }

    public void procurarTalentos(View view) {
        Intent intent  = new Intent(this, ProcurarTalentos.class);
        startActivity(intent);
    }

    public void gerenciarOportunidades(View view) {
        Intent intent  = new Intent(this, GerenciarOportunidades.class);
        startActivity(intent);
    }
}
