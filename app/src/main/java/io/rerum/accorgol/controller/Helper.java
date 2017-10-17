package io.rerum.accorgol.controller;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import io.rerum.accorgol.dao.EmpresarioDAO;
import io.rerum.accorgol.model.Empresario;
import io.rerum.accorgol.model.Jogador;

/**
 * Created by osman on 10/10/2017.
 */

public class Helper {

    private int count;
    private DatabaseReference mDatabase;

    public void verifyIdInDataBase(final String root, final Context ctx, final Empresario empresario, final Jogador jogador){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/"+root);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                String agenteFifa = dataSnapshot.child("agenteFifa").getValue(String.class);
                if (count == 0){
                    count = (int) dataSnapshot.getChildrenCount() + 1;
                    if (root.equals("Empresarios")){
                        EmpresarioDAO dao = new EmpresarioDAO(ctx);
                        dao.salvarID(count);
                        empresario.setIdEmpresario(count);
                        mDatabase.child("Empresarios").child(String.valueOf(count)).setValue(empresario/*.forName("Empresario")*/);
                    } else if (root.equals("Jogadores")){
                        EmpresarioDAO dao = new EmpresarioDAO(ctx);
                        dao.salvarID(count);
                        jogador.setIdJogador(count);
                        mDatabase.child("Jogadores").child(String.valueOf(count)).setValue(jogador/*.forName("Empresario")*/);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("COEEEE ERRROOOO", databaseError.toString());
            }
        });

    }

}
