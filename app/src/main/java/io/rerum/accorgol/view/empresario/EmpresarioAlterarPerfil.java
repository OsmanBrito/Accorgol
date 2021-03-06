package io.rerum.accorgol.view.empresario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.EmpresarioService;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.dao.UsuarioDAO;
import io.rerum.accorgol.model.Empresario;

/**
 * Created by osman on 05/10/2017.
 */

public class EmpresarioAlterarPerfil extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText dataNascimento;
    private EditText endereco;
    private EditText bairro;
    private EditText cidade;
    private Spinner estado;
    private EditText cep;
    private EditText celular;
    private EditText empresa;
    private EditText registro;
    private Empresario empresario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empresarioalterarperfil);

        nome = (EditText) findViewById(R.id.nomeAlterarEmpresario);
        email = (EditText) findViewById(R.id.emailAlterarEmpresario);
        dataNascimento = (EditText) findViewById(R.id.dataAlterarEmpresario);
        endereco = (EditText) findViewById(R.id.enderecoAlterarEmpresario);
        bairro = (EditText) findViewById(R.id.bairroAlterarEmpresario);
        cidade = (EditText) findViewById(R.id.cidadeAlterarEmpresario);
        estado = (Spinner) findViewById(R.id.spinner5);
        cep = (EditText) findViewById(R.id.cepAlterarEmpresario);
        celular = (EditText) findViewById(R.id.celularAlterarEmpresario);
        empresa = (EditText) findViewById(R.id.empresaAlterarEmpresario);
        registro = (EditText) findViewById(R.id.registroAlterarEmpresario);

        buscarEmpresario();
    }


    public void buscarEmpresario(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Empresarios/"+new FirebaseHelper().recuperar(this, String.valueOf(R.string.id_Usuario)));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String agenteFifa = dataSnapshot.child("agenteFifa").getValue(String.class);
                Log.e("COEEEE", "buscar empresario Ondatachange");

                bairro.setText(dataSnapshot.child("bairro").getValue(String.class));
                celular.setText(dataSnapshot.child("celular").getValue(String.class));
                cep.setText(dataSnapshot.child("cep").getValue(String.class));
                cidade.setText(dataSnapshot.child("cidade").getValue(String.class));
                dataNascimento.setText(dataSnapshot.child("dataDeNascimento").getValue(String.class));
                email.setText(dataSnapshot.child("email").getValue(String.class));
                empresa.setText(dataSnapshot.child("empresa").getValue(String.class));
                endereco.setText(dataSnapshot.child("endereco").getValue(String.class));
                nome.setText(dataSnapshot.child("nomeCompleto").getValue(String.class));
                registro.setText(dataSnapshot.child("numeroRegistro").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void AlterarDadosPerfilEmpresario(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Empresarios/"+new FirebaseHelper().recuperar(this, String.valueOf(R.string.id_Usuario)));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e("COEEEE", "AlterarDadosPerfil Ondatachange");

//                String agenteFifa = dataSnapshot.child("agenteFifa").getValue(String.class);

                myRef.child("nomeCompleto").setValue(nome.getText().toString());
                myRef.child("bairro").setValue(bairro.getText().toString());
                myRef.child("celular").setValue(celular.getText().toString());
                myRef.child("cep").setValue(cep.getText().toString());
                myRef.child("cidade").setValue(cidade.getText().toString());
                myRef.child("dataNascimento").setValue(dataNascimento.getText().toString());
                myRef.child("email").setValue(email.getText().toString());
                myRef.child("empresa").setValue(empresa.getText().toString());
                myRef.child("endereco").setValue(endereco.getText().toString());
                myRef.child("empresa").setValue(empresa.getText().toString());
                myRef.child("estado").setValue(estado.getSelectedItem().toString());
                myRef.child("numeroRegistro").setValue(registro.getText().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void VoltarParaPerfilEmpresario(View view) {

        this.finish();

    }
}
