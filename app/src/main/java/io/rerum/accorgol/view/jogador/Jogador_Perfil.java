package io.rerum.accorgol.view.jogador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.view.jogador.fragments.CarreiraFragment;
import io.rerum.accorgol.view.jogador.fragments.ComConquistaFragment;
import io.rerum.accorgol.view.jogador.fragments.JogadorPerfilFragment;
import io.rerum.accorgol.view.jogador.fragments.SemConquistaFragment;

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
    private boolean flag = false;

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
                android.app.FragmentManager manager = getFragmentManager();
               if (tabId == R.id.tab_perfil) {
                   Toast.makeText(getApplicationContext(), "perfil", Toast.LENGTH_LONG).show();
                   JogadorPerfilFragment jo = new JogadorPerfilFragment();
                   manager.beginTransaction().replace(R.id.contentContainer, jo, jo.getTag()).commit();
               } else if (tabId == R.id.tab_carreira) {
                    CarreiraFragment carreiraFragment = new CarreiraFragment();
                    manager.beginTransaction().replace(R.id.contentContainer, carreiraFragment, carreiraFragment.getTag()).commit();
                }
                else {
                   if (new FirebaseHelper().recuperar(getApplicationContext(), "TemConquista").equals("Tem")){
                       ComConquistaFragment semConquistaFragment = new ComConquistaFragment();
                       manager.beginTransaction().replace(R.id.contentContainer, semConquistaFragment, semConquistaFragment.getTag()).commit();

                   } else {
                       SemConquistaFragment semConquistaFragment = new SemConquistaFragment();
                       manager.beginTransaction().replace(R.id.contentContainer, semConquistaFragment, semConquistaFragment.getTag()).commit();
                   }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        verifyDB();
    }

//    public void verifyDB(){
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        UsuarioDAO dao = new UsuarioDAO(this);
//        int id = dao.getIDBanco();
//        DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Jogadores/"+id);
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                if (dataSnapshot.child("Carreira").getKey().equals("Carreira")){
//                    flag = true;
//                    CarreiraFragment carreiraFragment = new CarreiraFragment();
//                    android.app.FragmentManager manager = getFragmentManager();
//                    manager.beginTransaction().replace(R.id.contentContainer, carreiraFragment, carreiraFragment.getTag()).commit();
//                } else {
//                    Log.e("JOGADOR_PERFIL", dataSnapshot.getKey());
//                }
////                nome.setText(dataSnapshot.child("nomeCompleto").getValue(String.class));
////                dataNascimento.setText(dataSnapshot.child("anoNascimento").getValue(String.class));
////                posicao.setText(dataSnapshot.child("posicao").getValue(String.class));
////                pedominante.setText(dataSnapshot.child("peDominante").getValue(String.class));
////                rg.setText(dataSnapshot.child("rg").getValue(String.class));
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//            }
//        });
//    }

    public void pesquisarEmpresario(View view) {
        Intent myIntent = new Intent(this, PesquisarEmpresario.class);
        startActivity(myIntent);
    }
}
