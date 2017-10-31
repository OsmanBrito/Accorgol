package io.rerum.accorgol.controller;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.rerum.accorgol.model.Empresario;

/**
 * Created by osman on 26/09/2017.
 */

public class EmpresarioService{

    private int idEmpresario;

    private DatabaseReference mDatabase;

    public boolean cadastrarEmpresario(String nomeCompleto, String email, String senha, String cpf, String dataNascimento, String endereco, String bairro, String cidade, String estado, String cep, String celular, String empresa, String numeroRegistro, Context ctx){
        final Empresario empresario = new Empresario(nomeCompleto, email, cpf, dataNascimento, endereco, bairro, cidade, estado, cep, celular, senha, empresa, numeroRegistro);
        FirebaseHelper firebaseHelper = new FirebaseHelper();
        firebaseHelper.verifyIdInDataBase("Empresarios", ctx, empresario, null);
        return true;
    }

    public Empresario buscaEmpresarioBanco(Context ctx){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Empresarios/2");
        final Empresario[] empresario = new Empresario[1];
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String agenteFifa = dataSnapshot.child("agenteFifa").getValue(String.class);
                String bairro = dataSnapshot.child("bairro").getValue(String.class);
                String celular = dataSnapshot.child("celular").getValue(String.class);
                String cep = dataSnapshot.child("cep").getValue(String.class);
                String cidade = dataSnapshot.child("cidade").getValue(String.class);
                String cpf = dataSnapshot.child("cpf").getValue(String.class);
                String data = dataSnapshot.child("dataNascimento").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String empresa = dataSnapshot.child("empresa").getValue(String.class);
                String endereco = dataSnapshot.child("endereco").getValue(String.class);
                String estado = dataSnapshot.child("estado").getValue(String.class);
                String idEmpresario = dataSnapshot.child("idEmpresario").getValue(String.class);
                String nomeCompleto = dataSnapshot.child("nomeCompleto").getValue(String.class);
                String numeroRegistro = dataSnapshot.child("numeroRegistro").getValue(String.class);
                String senha = dataSnapshot.child("senha").getValue(String.class);

                empresario[0] = new Empresario(nomeCompleto, email, cpf, data, endereco, bairro, cidade, estado, cep, celular,empresa, numeroRegistro);
                Log.e("COEEEE", empresario[0].getBairro());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
//        UsuarioDAO dao = new UsuarioDAO(ctx);
//        return dao.retornaEmpresario();
        return empresario[0];
    }

    public void alterarPerfilEmpresario(Empresario empresario, Context ctx) {

    }
}
