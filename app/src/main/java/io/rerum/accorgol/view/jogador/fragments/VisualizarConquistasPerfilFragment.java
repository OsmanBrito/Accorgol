package io.rerum.accorgol.view.jogador.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.model.Conquista;

/**
 * Created by osman on 28/11/2017.
 */

public class VisualizarConquistasPerfilFragment extends Fragment {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Conquista> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.com_conquista_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        data = new ArrayList<Conquista>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("/Jogadores/"+new FirebaseHelper().recuperar(view.getContext(), String.valueOf(R.string.id_jogador_que_sera_visualizado)) + "/Conquistas/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    Log.d("USUARIOOOO POSTSNAPSHOOOT", String.valueOf(postSnapshot.getValue()));
                    Conquista conquista = new Conquista();
                    conquista.setNomeClube(postSnapshot.child("nomeClube").getValue(String.class));
                    conquista.setAno(postSnapshot.child("ano").getValue(String.class));
                    conquista.setNomeConquista(postSnapshot.child("nomeConquista").getValue(String.class));
                    conquista.setURIFoto(postSnapshot.child("urifoto").getValue(String.class));
                    Log.d("USUARIOOOO", conquista.getNomeClube());

                    data.add(conquista);
                }
                adapter = new CustomAdapter(data);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

}
