package io.rerum.accorgol.view.empresario;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.model.Conquista;
import io.rerum.accorgol.model.Empresario;
import io.rerum.accorgol.model.Jogador;
import io.rerum.accorgol.view.jogador.RVAdapter;
import io.rerum.accorgol.view.jogador.fragments.CustomAdapter;

/**
 * Created by osman on 31/10/2017.
 */

public class ResultadoBuscaDeTalentos extends AppCompatActivity {

    private Context ctx;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Jogador> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resultadobuscajogador);
        ctx = this.getApplicationContext();
        Intent intent = getIntent();
        String posicao = intent.getStringExtra("posicao");
        String ano = intent.getStringExtra("ano");
        String pe = intent.getStringExtra("pe");
        final Jogador jogador = new Jogador(posicao, pe, ano);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(ctx);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<Jogador>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Jogadores");
        final Empresario[] empresario = new Empresario[1];
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Log.e("USUARIOOOO VER VALORES DENTRO DO BUSCAR JOGADOR = ", String.valueOf(postSnapshot.getValue()));
                    Jogador j = new Jogador(postSnapshot.child("posicao").getValue(String.class), postSnapshot.child("peDominante").getValue(String.class), postSnapshot.child("anoNascimento").getValue(String.class));
                    if (jogador.getAnoNascimento().equals(j.getAnoNascimento()) && jogador.getPosicao().equals(j.getPosicao()) && jogador.getPeDominante().equals(j.getPeDominante())) {
                        j.setNomeCompleto(postSnapshot.child("nomeCompleto").getValue(String.class));
                        Log.e("USUARIOOOOOO JOGADORES SELECIONADOS!!", j.getNomeCompleto());
                        if (postSnapshot.child("urifoto") != null && postSnapshot.child("urivideo") != null) {
                            j.setVideojogador(postSnapshot.child("urivideo").getValue(String.class));
                            j.setFotoJogador(postSnapshot.child("urifoto").getValue(String.class));
                        } else {
                            if (postSnapshot.child("urifoto") != null) {
                                j.setFotoJogador(postSnapshot.child("urifoto").getValue(String.class));
                            } else if (postSnapshot.child("urivideo") != null) {
                                j.setVideojogador(postSnapshot.child("urivideo").getValue(String.class));
                            }
                        }
                        if (postSnapshot.child("Conquistas") != null && postSnapshot.child("Carreira") != null) {

                            for (DataSnapshot carreiraPost: postSnapshot.child("Carreira").getChildren()) {
                                Log.e("USUARIOOOOOOOOO" , carreiraPost.getValue().toString());
                            }

                        }
                        Toast.makeText(ctx, "Fmz meu!!!", Toast.LENGTH_SHORT).show();
                        i++;
                        //TODO ARRUMAR PARA SER SO STRING DEPOIS!!!!!
                        j.setIdJogador(String.valueOf(postSnapshot.child("idJogador").getValue(Long.class)));
                        data.add(j);
                    }
                }
                if (i == 0) {
                    Toast.makeText(ctx, "Nenhum Jogador encontrado!", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Log.i("USUARIOOOO ver o data antes de enviar para o adapter!!", String.valueOf(data.listIterator()));
                    adapter = new BuscarTalentosAdapter(data);
                    recyclerView.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public void verPerfilJogador(View view) {
        Intent myIntent = new Intent(this, VerPerfilJogador.class);
        startActivity(myIntent);
    }

    public void voltarTela(View view) {
        this.finish();
    }
}
