package io.rerum.accorgol.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.rerum.accorgol.R;
import io.rerum.accorgol.view.empresario.Empresario_form;
import io.rerum.accorgol.view.jogador.Jogador_Form;

public class MainActivity extends AppCompatActivity {

    private String email;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        this.email = intent.getStringExtra("email");
        this.senha = intent.getStringExtra("senha");
    }

    public void EmpresarioButton(View view) {
        Intent myIntent = new Intent(this, Empresario_form.class);
        myIntent.putExtra("email", email);
        myIntent.putExtra("senha", senha);
        MainActivity.this.startActivity(myIntent);
    }

    public void JogadorButton(View view) {
        Intent myIntent = new Intent(this, Jogador_Form.class);
        myIntent.putExtra("email", email);
        myIntent.putExtra("senha", senha);
        MainActivity.this.startActivity(myIntent);
    }
}
