package io.rerum.accorgol.view.jogador.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.dao.UsuarioDAO;

/**
 * Created by osman on 28/11/2017.
 */

public class VisualizarPerfilDadosPessoaisFragment extends Fragment {

    private ImageView foto;
    private TextView nomeJogador;
    private TextView posicao;
    private TextView pe;
    private TextView ano;
    private TextView rg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.visualizar_dados_pessoais, container, false);

        foto = (ImageView) view.findViewById(R.id.visualizarFotoDadosPessoais);
        nomeJogador = (TextView) view.findViewById(R.id.visualizarNomeJogadorPerfil);
        posicao = (TextView) view.findViewById(R.id.visualizarPosicaoJogadorPerfil);
        pe = (TextView) view.findViewById(R.id.visualizarPeJogadorPerfil);
        ano = (TextView) view.findViewById(R.id.visualizarAnoPerfilJogador);
        rg = (TextView) view.findViewById(R.id.visualizarRGPerfilJogador);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String id = (new FirebaseHelper().recuperar(view.getContext(), String.valueOf(R.string.id_jogador_que_sera_visualizado)));
        DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Jogadores/"+id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("USUARIOOOO VER DENTRO DO VISUALIARPERFILDADOSPESSOAISFRAGMENT!!!", String.valueOf(dataSnapshot.child("nomecompleto")));
                nomeJogador.setText(dataSnapshot.child("nomeCompleto").getValue(String.class));
                posicao.setText(dataSnapshot.child("posicao").getValue(String.class));
                pe.setText(dataSnapshot.child("peDominante").getValue(String.class));
                ano.setText(dataSnapshot.child("anoNascimento").getValue(String.class));
                rg.setText(dataSnapshot.child("rg").getValue(String.class));

                if (dataSnapshot.child("urifoto").getValue(String.class) != null){
                    Picasso.with(view.getContext()).load(dataSnapshot.child("urifoto").getValue(String.class)).into(foto);
                } else {
                    Picasso.with(view.getContext()).load(R.mipmap.ic_launcher).into(foto);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return view;
    }
}
