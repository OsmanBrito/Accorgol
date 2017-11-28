package io.rerum.accorgol.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

import io.rerum.accorgol.R;
import io.rerum.accorgol.dao.UsuarioDAO;
import io.rerum.accorgol.model.Empresario;
import io.rerum.accorgol.model.Jogador;

/**
 * Created by osman on 10/10/2017.
 */

public class FirebaseHelper implements Serializable{

    private int count;
    private DatabaseReference mDatabase;

    public void verifyIdInDataBase(final String root, final Context ctx, final Empresario empresario, final Jogador jogador){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/"+root);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (count == 0) {
                    count = (int) dataSnapshot.getChildrenCount() + 1;
                    UsuarioDAO dao = new UsuarioDAO(ctx);
                    dao.salvarID(count);
                    armazenar(ctx, String.valueOf(count), String.valueOf(R.string.id_Usuario));

                    if (root.equals("Empresarios")) {
                        empresario.setIdEmpresario(count);
                        mDatabase.child(root).child(String.valueOf(count)).setValue(empresario);
                    } else {
                        jogador.setIdJogador(count);
                        mDatabase.child(root).child(String.valueOf(count)).setValue(jogador);
                        //aqui sera setado o id do usuario para poder colocar nas URLs do fireabse, pegando fotos e videos DO USUARIO
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("COEEEE ERRROOOO", databaseError.toString());
            }
        });
    }

    public static final String PREFS_NAME = "MyPrefsFile";

    public void armazenar(Context ctx, String  value, String nameFile){
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nameFile, value);
        Log.e("USUARIO AQUI NO ARMAZENAR!!!", value);
        editor.apply();
    }

    public String recuperar(Context context, String nameFile){
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        String result = settings.getString(nameFile, "");
        Log.e("USUARIO RESULT RECUPERAR!!! "+nameFile, result);

        return result;
    }
}
