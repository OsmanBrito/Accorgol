package io.rerum.accorgol.view.jogador;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import io.rerum.accorgol.R;
import io.rerum.accorgol.dao.UsuarioDAO;
import io.rerum.accorgol.view.jogador.fragments.PerfilFragment;

/**
 * Created by osman on 17/10/2017.
 */

public class Jogador_Perfil extends AppCompatActivity{

    private TextView nome;
    private TextView rg;
    private TextView dataNascimento;
    private TextView posicao;
    private TextView pedominante;
    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogadorperfil);
        nome = (TextView) findViewById(R.id.nomePerfilJogador);
        rg = (TextView) findViewById(R.id.rgPerfilJogador);
        dataNascimento = (TextView) findViewById(R.id.anoPerfiljogador);
        posicao = (TextView) findViewById(R.id.posicaoPerfilJogador);
        pedominante = (TextView) findViewById(R.id.pePerfilJogador);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {
                if (tabId == R.id.tab_perfil) {
                    Toast.makeText(getApplicationContext(), "perfil", Toast.LENGTH_LONG).show();
                    PerfilFragment perfilFragment = new PerfilFragment();
                    android.app.FragmentManager manager = getFragmentManager();
                    manager.beginTransaction().replace(R.id.contentContainer, perfilFragment, perfilFragment.getTag()).commit();
                } else if (tabId == R.id.tab_carreira) {
                    Toast.makeText(getApplicationContext(), "conquista", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "carreira", Toast.LENGTH_LONG).show();
                }
            }
        });
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
//                nome.setText(dataSnapshot.child("nomeCompleto").getValue(String.class));
//                dataNascimento.setText(dataSnapshot.child("anoNascimento").getValue(String.class));
//                posicao.setText(dataSnapshot.child("posicao").getValue(String.class));
//                pedominante.setText(dataSnapshot.child("peDominante").getValue(String.class));
//                rg.setText(dataSnapshot.child("rg").getValue(String.class));
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
