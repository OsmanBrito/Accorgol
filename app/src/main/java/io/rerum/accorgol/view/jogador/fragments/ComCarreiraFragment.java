package io.rerum.accorgol.view.jogador.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.model.Carreira;
import io.rerum.accorgol.model.Conquista;

/**
 * Created by osman on 28/11/2017.
 */

public class ComCarreiraFragment extends Fragment{

    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;
    HashMap<String, List<String>> hashMap;
    ArrayList<String> header;
    private BootstrapButton bootstrapButton;
    private Context ctx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.com_carreira_fragment,
                container, false);
        this.ctx = view.getContext();
        expandableListView = (ExpandableListView) view.findViewById(R.id.simple_expandable_listview);
        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);
        setItems(view.getContext());
        bootstrapButton = (BootstrapButton) view.findViewById(R.id.addNewCarreira);
        bootstrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.app.FragmentManager manager = getFragmentManager();
                AddCarreiraFragment cc = new AddCarreiraFragment();
                manager.beginTransaction().replace(R.id.contentContainer, cc, cc.getTag()).commit();
            }
        });
        return view;
    }

    // Setting headers and childs to expandable listview
    void setItems(final Context ctx) {

        // Array list for header
        header = new ArrayList<String>();

        // Array list for child items
        List<String> child2 = new ArrayList<String>();
        List<String> child3 = new ArrayList<String>();
        List<String> child4 = new ArrayList<String>();

        // Hash map for both header and child
        hashMap = new HashMap<String, List<String>>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("/Jogadores/"+new FirebaseHelper().recuperar(ctx, String.valueOf(R.string.id_Usuario)) + "/Carreira/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("Count " ,""+dataSnapshot.getChildrenCount());
                int i = 0;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    List<String> child = new ArrayList<String>();
                    Log.d("USUARIOOOO POSTSNAPSHOOOT", String.valueOf(postSnapshot.getValue()));
                    Boolean atual = postSnapshot.child("atual").getValue(Boolean.class);
                    String  de = postSnapshot.child("de").getValue(String .class);
                    String  nomeClube = postSnapshot.child("nomeClubeCarreira").getValue(String .class);
                    String  posicao = postSnapshot.child("posicaoClubeCarreira").getValue(String .class);
                    int assistencia = postSnapshot.child("qntdAssistencia").getValue(Integer.class);
                    int gols = postSnapshot.child("qntdGols").getValue(Integer.class);
                    int sofridos = postSnapshot.child("qntdGolsSofridos").getValue(Integer.class);
                    int partidas = postSnapshot.child("qntdPartidas").getValue(Integer.class);
                    Log.d("USUARIOOOO POSTSNAPSHOOOT 2 ", String.valueOf(partidas) + nomeClube + assistencia + atual);
                    header.add("Clube " + nomeClube);
                    child.add("Posição: "+ posicao);
                    child.add("Inicio: "+ de);
                    child.add("Clube atual: "+ atual);
                    child.add("Numero de gols: "+ String.valueOf(gols));
                    child.add("Assistencias: "+ String.valueOf(assistencia));
                    child.add("Partidas: "+ String.valueOf(partidas));
                    if (posicao.equals("Goleiro") || posicao.equals("Zagueiro")) {
                        child.add("Gols sofridos: "+ String.valueOf(sofridos));
                    }
                    hashMap.put(header.get(i), child);
                    i++;
                    Log.e("USUARIOOOO VER O HASHMAP = ", String.valueOf(hashMap));
                }

                adapter = new ExpandableListAdapter(ctx, header, hashMap);
                // Setting adpater for expandablelistview
                expandableListView.setAdapter(adapter);

        /*
        You can add listeners for the item clicks
         */
                expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

                    @Override
                    public boolean onGroupClick(ExpandableListView parent, View v,
                                                int groupPosition, long id) {
                        return false;
                    }
                });

                // Listview Group expanded listener
                expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                    @Override
                    public void onGroupExpand(int groupPosition) {

                    }
                });

                // Listview Group collasped listener
                expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

                    @Override
                    public void onGroupCollapse(int groupPosition) {

                    }
                });

                // Listview on child click listener
                expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v,
                                                int groupPosition, int childPosition, long id) {
                        return false;
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
