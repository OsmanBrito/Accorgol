package io.rerum.accorgol.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.roughike.bottombar.BottomBar;

import io.rerum.accorgol.R;
import io.rerum.accorgol.view.jogador.fragments.ComConquistaFragment;
import io.rerum.accorgol.view.jogador.fragments.JogadorPerfilFragment;
import io.rerum.accorgol.view.jogador.fragments.SemConquistaFragment;
import io.rerum.accorgol.view.jogador.fragments.VisualizarCarreiraPerfilFragment;
import io.rerum.accorgol.view.jogador.fragments.VisualizarConquistasPerfilFragment;
import io.rerum.accorgol.view.jogador.fragments.VisualizarPerfilDadosPessoaisFragment;
import io.rerum.accorgol.view.jogador.fragments.VisualizarVideoFragment;

/**
 * Created by osman on 28/11/2017.
 */

public class VisualizarPerfilJogador extends AppCompatActivity {

    private TextView nome;
    private TextView rg;
    private TextView dataNascimento;
    private TextView posicao;
    private TextView pedominante;
    private BottomBar bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.visualizar_perfil_jogador);
        android.app.FragmentManager manager = getFragmentManager();
        VisualizarPerfilDadosPessoaisFragment vj = new VisualizarPerfilDadosPessoaisFragment();
        VisualizarVideoFragment vv = new VisualizarVideoFragment();
        VisualizarCarreiraPerfilFragment vc = new VisualizarCarreiraPerfilFragment();
        VisualizarConquistasPerfilFragment cc = new VisualizarConquistasPerfilFragment();

        manager.beginTransaction().replace(R.id.contentDadosPessoais, vj, vj.getTag()).commit();
        manager.beginTransaction().replace(R.id.contentVideoJogador, vv, vv.getTag()).commit();
        manager.beginTransaction().replace(R.id.contentCarreiraJogador, vc, vc.getTag()).commit();
        manager.beginTransaction().replace(R.id.contentConquistasJogador, cc, cc.getTag()).commit();

    }
}
