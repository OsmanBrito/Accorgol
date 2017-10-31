package io.rerum.accorgol.view.empresario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Date;

import io.rerum.accorgol.R;

/**
 * Created by osman on 13/08/2017.
 */

public class Empresario_form extends AppCompatActivity{

    private Spinner estado;
    private EditText nome;
    private EditText celular;
    private EditText cpf;
    private EditText dataNascimento;
    private String email;
    private String senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empresario_form);
        this.nome = (EditText) findViewById(R.id.EmpresarioNomeCompleto);
        this.cpf = (EditText)  findViewById(R.id.cpf);
        this.dataNascimento = (EditText)  findViewById(R.id.dataNascimentoEmpresario);
        this.estado = (Spinner) findViewById(R.id.EmpresarioEstado);
        this.celular = (EditText) findViewById(R.id.EmpresarioPhone);
        Intent intent = getIntent();
        this.email = intent.getStringExtra("email");
        this.senha = intent.getStringExtra("senha");
    }

    public void empresarioCadastradoPrimeiraEtapa(View view) {
        String nome = this.nome.getText().toString();
        String estado = this.estado.getSelectedItem().toString();
        String celular = this.celular.getText().toString();
        String cpf = this.cpf.getText().toString();
        String dataNascimento = this.dataNascimento.getText().toString();
        Intent myIntent = new Intent(this, Empresario_form_segunda_etapa.class);
        myIntent.putExtra("email", email);
        myIntent.putExtra("senha", senha);
        myIntent.putExtra("nome", nome);
        myIntent.putExtra("estado", estado);
        myIntent.putExtra("celular", celular);
        myIntent.putExtra("cpf", cpf);
        myIntent.putExtra("dataNascimento", dataNascimento);
        startActivity(myIntent);
    }
}
