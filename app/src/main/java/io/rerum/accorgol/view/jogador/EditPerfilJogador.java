package io.rerum.accorgol.view.jogador;

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
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.model.Empresario;

/**
 * Created by osman on 30/11/2017.
 */

public class EditPerfilJogador extends AppCompatActivity {

    private EditText nome;
    private EditText posicao;
    private EditText pe;
    private EditText ano;
    private EditText rg;
    private EditText cidade;
    private Spinner estado;
    private EditText altura;
    private EditText celular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empresarioalterarperfil);

        nome = (EditText) findViewById(R.id.nomeAlterarEmpresario);
        posicao = (EditText) findViewById(R.id.emailAlterarEmpresario);
        pe = (EditText) findViewById(R.id.dataAlterarEmpresario);
        ano = (EditText) findViewById(R.id.enderecoAlterarEmpresario);
        rg = (EditText) findViewById(R.id.bairroAlterarEmpresario);

        cidade = (EditText) findViewById(R.id.cidadeAlterarEmpresario);
        estado = (Spinner) findViewById(R.id.spinner5);
        altura = (EditText) findViewById(R.id.cepAlterarEmpresario);
        celular = (EditText) findViewById(R.id.celularAlterarEmpresario);

        buscarEmpresario();
    }


    public void buscarEmpresario(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Jogadores/"+new FirebaseHelper().recuperar(this, String.valueOf(R.string.id_Usuario)));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String agenteFifa = dataSnapshot.child("agenteFifa").getValue(String.class);
                Log.e("COEEEE", "buscar empresario Ondatachange");

                rg.setText(dataSnapshot.child("rg").getValue(String.class));
                celular.setText(dataSnapshot.child("celular").getValue(String.class));
                altura.setText(dataSnapshot.child("altura").getValue(String.class));
                cidade.setText(dataSnapshot.child("cidade").getValue(String.class));
                pe.setText(dataSnapshot.child("peDominante").getValue(String.class));
                posicao.setText(dataSnapshot.child("posicao").getValue(String.class));
                ano.setText(dataSnapshot.child("anoNascimento").getValue(String.class));
                nome.setText(dataSnapshot.child("nomeCompleto").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void AlterarDadosPerfilEmpresario(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Jogadores/"+new FirebaseHelper().recuperar(this, String.valueOf(R.string.id_Usuario)));

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e("COEEEE", "AlterarDadosPerfil Ondatachange");

//                String agenteFifa = dataSnapshot.child("agenteFifa").getValue(String.class);

                myRef.child("nomeCompleto").setValue(nome.getText().toString());
                myRef.child("rg").setValue(rg.getText().toString());
                myRef.child("celular").setValue(celular.getText().toString());
                myRef.child("altura").setValue(altura.getText().toString());
                myRef.child("cidade").setValue(cidade.getText().toString());
                myRef.child("peDominante").setValue(pe.getText().toString());
                myRef.child("posicao").setValue(posicao.getText().toString());
                myRef.child("anoNascimento").setValue(ano.getText().toString());
                myRef.child("estado").setValue(estado.getSelectedItem().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
