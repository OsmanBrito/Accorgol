package io.rerum.accorgol.view.empresario.fragments;

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
import io.rerum.accorgol.model.Oportunidade;
import io.rerum.accorgol.view.jogador.fragments.CustomAdapter;

/**
 * Created by osman on 30/11/2017.
 */

public class ComOportunidadeFragment extends Fragment {
        private static RecyclerView.Adapter adapter;
        private RecyclerView.LayoutManager layoutManager;
        private static RecyclerView recyclerView;
        private static ArrayList<Oportunidade> data;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.com_conquista_fragment, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
            recyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            data = new ArrayList<Oportunidade>();
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef = database.getReference("/Empresarios/"+new FirebaseHelper().recuperar(view.getContext(), String.valueOf(R.string.id_Usuario)) + "/Oportunidades/");
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null) {
                        Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            Log.d("USUARIOOOO POSTSNAPSHOOOT", String.valueOf(postSnapshot.getValue()));
                            Oportunidade oportunidade = new Oportunidade();
                            oportunidade.setAlturaMin(postSnapshot.child("alturaMin").getValue(String.class));
                            oportunidade.setAnoNascimento(postSnapshot.child("anoNascimento").getValue(String.class));
                            oportunidade.setCidade(postSnapshot.child("cidade").getValue(String.class));
                            oportunidade.setEstado(postSnapshot.child("estado").getValue(String.class));
                            oportunidade.setPeDominante(postSnapshot.child("peDominante").getValue(String.class));
                            oportunidade.setPosicao(postSnapshot.child("posicao").getValue(String.class));
                            oportunidade.setIdOportunidade(postSnapshot.child("idOportunidade").getValue(String.class));
                            data.add(oportunidade);
                        }
                        adapter = new OportunidadeAdapter(data);
                        recyclerView.setAdapter(adapter);
                    } else {
                        android.app.FragmentManager manager = getFragmentManager();
                        SemOportunidadeFragment so = new SemOportunidadeFragment();
                        manager.beginTransaction().replace(R.id.contentOportunidades, so, so.getTag()).commit();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            return view;
        }
}