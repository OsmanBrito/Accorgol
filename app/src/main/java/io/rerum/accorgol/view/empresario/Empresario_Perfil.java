package io.rerum.accorgol.view.empresario;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.dao.UsuarioDAO;

/**
 * Created by osman on 03/10/2017.
 */

public class Empresario_Perfil extends AppCompatActivity {

    private TextView nome;
    private TextView email;
    private TextView cpf;
    private TextView dataNascimento;
    private TextView endereco;
    private TextView bairro;
    private TextView cidade;
    private TextView estado;
    private TextView cep;
    private TextView celular;
    private TextView empresa;
    private TextView registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empresarioperfil);

        nome = (TextView) findViewById(R.id.NomeEmpresarioPerfil);
        email = (TextView) findViewById(R.id.EmailEmpresarioPerfil);
        cpf = (TextView) findViewById(R.id.CpfEmpresarioPerfil);
        dataNascimento = (TextView) findViewById(R.id.dataEmpresarioPerfil);
        endereco = (TextView) findViewById(R.id.EnderecoEmpresarioPerfil);
        bairro = (TextView) findViewById(R.id.BairroEmpresarioPerfil);
        cidade = (TextView) findViewById(R.id.CidadeEmpresarioPerfil);
        estado = (TextView) findViewById(R.id.EstadoEmpresarioPerfil);
        cep = (TextView) findViewById(R.id.CepEmpresarioPerfil);
        celular = (TextView) findViewById(R.id.CelularEmpresarioPerfil);
        empresa = (TextView) findViewById(R.id.EmpresaEmpresarioPerfil);
        registro = (TextView) findViewById(R.id.RegistroEmpresarioPerfil);

    }

    @Override
    protected void onStart() {
        buscaEmpresarioBanco();
        super.onStart();
    }

    public void AlterarPerfilEmpresario(View view) {

        Intent intent = new Intent(this, EmpresarioAlterarPerfil.class);
        startActivity(intent);

    }

    public void buscaEmpresarioBanco(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        String id = new FirebaseHelper().recuperar(this, String.valueOf(R.string.id_Usuario));

        DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Empresarios/"+id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String agenteFifa = dataSnapshot.child("agenteFifa").getValue(String.class);
                bairro.setText(dataSnapshot.child("bairro").getValue(String.class));
                celular.setText(dataSnapshot.child("celular").getValue(String.class));
                cep.setText(dataSnapshot.child("cep").getValue(String.class));
                cidade.setText(dataSnapshot.child("cidade").getValue(String.class));
                cpf.setText(dataSnapshot.child("cpf").getValue(String.class));
                dataNascimento.setText(dataSnapshot.child("dataDeNascimento").getValue(String.class));
                email.setText(dataSnapshot.child("email").getValue(String.class));
                empresa.setText(dataSnapshot.child("empresa").getValue(String.class));
                endereco.setText(dataSnapshot.child("endereco").getValue(String.class));
                estado.setText(dataSnapshot.child("estado").getValue(String.class));
                nome.setText(dataSnapshot.child("nomeCompleto").getValue(String.class));
                registro.setText(dataSnapshot.child("numeroRegistro").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
