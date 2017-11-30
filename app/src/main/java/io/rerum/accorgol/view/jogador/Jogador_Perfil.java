package io.rerum.accorgol.view.jogador;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.view.VisualizarPerfilJogador;
import io.rerum.accorgol.view.empresario.AddOportunidade;
import io.rerum.accorgol.view.jogador.fragments.ComCarreiraFragment;
import io.rerum.accorgol.view.jogador.fragments.ComConquistaFragment;
import io.rerum.accorgol.view.jogador.fragments.JogadorPerfilFragment;
import io.rerum.accorgol.view.jogador.fragments.SemConquistaFragment;
import io.rerum.accorgol.view.jogador.fragments.SemDadoCarreira;

/**
 * Created by osman on 17/10/2017.
 */

public class Jogador_Perfil extends AppCompatActivity{

    private BottomBar bottomBar;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogadorperfil);
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
                   if (new FirebaseHelper().recuperar(getApplicationContext(), String.valueOf(R.string.tem_carreira)).equals("Tem")){
                       Log.i("USUARIOOOO tem carreira", "tem carreira");
                       ComCarreiraFragment comCarreiraFragment = new ComCarreiraFragment();
                       manager.beginTransaction().replace(R.id.contentContainer, comCarreiraFragment, comCarreiraFragment.getTag()).commit();

                   } else {
                       SemDadoCarreira semDadoCarreira = new SemDadoCarreira();
                       manager.beginTransaction().replace(R.id.contentContainer, semDadoCarreira, semDadoCarreira.getTag()).commit();
                   }
                }
                else {
                   if (new FirebaseHelper().recuperar(getApplicationContext(), String.valueOf(R.string.tem_conquista)).equals("Tem")){
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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.add_item) {
            //check if any items to add
            if (String.valueOf(R.string.id_jogador_que_sera_visualizado).equals("")) {
                Intent intent = new Intent(this, AddOportunidade.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, VisualizarPerfilJogador.class);
                String id = new FirebaseHelper().recuperar(this, String.valueOf(R.string.id_Usuario));
                new FirebaseHelper().armazenar(this, id, String.valueOf(R.string.id_jogador_que_sera_visualizado));
                startActivity(intent);
            }

        }
        return true;
    }
}
