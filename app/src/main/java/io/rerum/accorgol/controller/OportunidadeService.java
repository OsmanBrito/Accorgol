package io.rerum.accorgol.controller;

import android.content.Context;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.rerum.accorgol.R;
import io.rerum.accorgol.model.Carreira;
import io.rerum.accorgol.model.Oportunidade;

/**
 * Created by osman on 29/11/2017.
 */

public class OportunidadeService {

    private DatabaseReference mDatabase;
    private int count;

    public void addOportunidade(final Oportunidade oportunidade, final String root, final Context ctx) {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.w("USUARIO VER O ROOT DE addCarreira", root);
        DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/"+root);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (count == 0) {
                    count = (int) dataSnapshot.getChildrenCount() + 1;
                    new FirebaseHelper().armazenar(ctx, "Tem", String.valueOf(R.string.tem_carreira));
                    mDatabase.child(root).child(""+String.valueOf(count)).setValue(oportunidade);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("COEEEE ERRROOOO", databaseError.toString());
            }
        });
    }
}
