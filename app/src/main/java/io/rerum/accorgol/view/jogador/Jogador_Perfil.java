package io.rerum.accorgol.view.jogador;

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
import io.rerum.accorgol.dao.UsuarioDAO;

/**
 * Created by osman on 17/10/2017.
 */

public class Jogador_Perfil extends AppCompatActivity{

    private TextView nome;
    private TextView rg;
    private TextView dataNascimento;
    private TextView posicao;
    private TextView pedominante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogadorperfil);
        nome = (TextView) findViewById(R.id.nomePerfilJogador);
        rg = (TextView) findViewById(R.id.rgPerfilJogador);
        dataNascimento = (TextView) findViewById(R.id.anoPerfiljogador);
        posicao = (TextView) findViewById(R.id.posicaoPerfilJogador);
        pedominante = (TextView) findViewById(R.id.pePerfilJogador);
    }

    @Override
    protected void onStart() {
        super.onStart();
        buscaJogador();
    }

    public void buscaJogador(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        UsuarioDAO dao = new UsuarioDAO(this);
        int id = dao.getIdUsuario();

        DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Jogadores/"+id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nome.setText(dataSnapshot.child("nomeCompleto").getValue(String.class));
                dataNascimento.setText(dataSnapshot.child("anoNascimento").getValue(String.class));
                posicao.setText(dataSnapshot.child("posicao").getValue(String.class));
                pedominante.setText(dataSnapshot.child("peDominante").getValue(String.class));
                rg.setText(dataSnapshot.child("rg").getValue(String.class));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void pesquisarEmpresario(View view) {
        Intent myIntent = new Intent(this, PesquisarEmpresario.class);
        startActivity(myIntent);
    }
}
