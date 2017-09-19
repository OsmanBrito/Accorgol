package io.rerum.accorgol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void EmpresarioButton(View view) {
        Intent myIntent = new Intent(this, Empresario_form.class);
        MainActivity.this.startActivity(myIntent);
    }

    public void JogadorButton(View view) {
        Intent myIntent = new Intent(this, Jogador_Form.class);
        MainActivity.this.startActivity(myIntent);
    }
}
