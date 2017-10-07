package io.rerum.accorgol.view.empresario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Date;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.EmpresarioService;

/**
 * Created by osman on 26/09/2017.
 */

public class Empresario_form_segunda_etapa extends AppCompatActivity {

    private String email;
    private String senha;
    private String nome;
    private String cpf;
    private String estado;
    private String celular;
    private String dataNascimento;

    private EditText bairro;
    private EditText endereco;
    private EditText cidade;
    private EditText cep;
    private EditText empresa;
//    private EditText site_da_empresa;
    private EditText agentefifa;
    private EditText numero_registro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empresario_form_segunda_etapa);

        Intent intent = getIntent();
        this.email = intent.getStringExtra("email");
        this.senha = intent.getStringExtra("senha");
        this.nome = intent.getStringExtra("nome");
        this.cpf = intent.getStringExtra("cpf");
        this.estado = intent.getStringExtra("estado");
        this.celular = intent.getStringExtra("celular");
        this.dataNascimento = intent.getStringExtra("dataNascimento");

        this.bairro = (EditText) findViewById(R.id.EmpresarioBairro);
        this.endereco = (EditText) findViewById(R.id.EmpresarioEndereco);
        this.cidade = (EditText) findViewById(R.id.EmpresarioCidade);
        this.cep = (EditText) findViewById(R.id.EmpresarioCEP);
        this.empresa = (EditText) findViewById(R.id.EmpresarioEmpresa);
        this.numero_registro = (EditText) findViewById(R.id.EmpresarioRegistro);


        Log.e("COEEE3232E", email);



    }

    public void PularSegundaEtapaCadastro(View view) {

        Intent intent = new Intent(this, EmpresarioHome.class);
        startActivity(intent);

    }


    public void ConcluirCadastro(View view) {

        String bairro = this.bairro.getText().toString();
        String endereco = this.endereco.getText().toString();
        String cidade = this.cidade.getText().toString();
        String cep = this.cep.getText().toString();
        String empresa = this.empresa.getText().toString();
        String numeroRegistro = this.numero_registro.getText().toString();

        Log.e("COEEEE", email + bairro);

        EmpresarioService empresarioService = new EmpresarioService();
        if (empresarioService.cadastrarEmpresario(nome, email, senha, cpf, dataNascimento, endereco, bairro, cidade, estado, cep, celular, empresa, numeroRegistro, getBaseContext())){
            Intent intent = new Intent(this, EmpresarioHome.class);

            Log.e("COEEEE", "startActivity!!");

            startActivity(intent);
        }

    }
}
