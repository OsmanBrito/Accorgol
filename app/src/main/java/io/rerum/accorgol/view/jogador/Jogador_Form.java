package io.rerum.accorgol.view.jogador;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

import io.rerum.accorgol.R;

/**
 * Created by osman on 13/08/2017.
 */

public class Jogador_Form extends AppCompatActivity{

    private Firebase mRootRef;

    private boolean flag;

    private String TAG = "LOG AQUI ACCORGOL";

    private EditText nameJogador;
    private EditText emailJogador;
    private EditText yearJogador;
    private EditText RG;
    private Spinner estadoJogador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jogador_form);
        Firebase.setAndroidContext(this);
        mRootRef = new Firebase("https://accorgol-5000e.firebaseio.com/Jogador");

        nameJogador = (EditText) findViewById(R.id.JogadorNome);
        emailJogador = (EditText) findViewById(R.id.JogadorEmail);
        yearJogador = (EditText) findViewById(R.id.JogadorYear);
        RG = (EditText) findViewById(R.id.RG);
        estadoJogador = (Spinner) findViewById(R.id.JogadoEstado);
    }

    public void JogadorCadastrado(View view) {

        String name = nameJogador.getText().toString();
        String email = emailJogador.getText().toString();
        String year = yearJogador.getText().toString();
        String estado = estadoJogador.getSelectedItem().toString();
        String rg = RG.getText().toString();



        if (verifyRG(rg)){
            Firebase id = mRootRef.child(rg);

            id.child("Nome").setValue(name);
            id.child("Email").setValue(email);
            id.child("Nascimento").setValue(year);
            id.child("RG").setValue(rg);
            id.child("Estado").setValue(estado);

            Toast.makeText(this, "Cadastrado com sucesso!!",
                    Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "RG Ja cadastradoo!!!",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public boolean verifyRG(String rg2){

        rg2 = "https://accorgol-5000e.firebaseio.com/Jogador/" + rg2;
        Firebase newRoot = new Firebase(rg2);

        Log.e(TAG, rg2);

        newRoot.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try{
                    Map<String, String> map = dataSnapshot.getValue(Map.class);
                    Log.e(TAG + "Dentro do Try rg = ", map.get("RG"));
                    flag = true;
                //se der um erro será pq nao veio nada no Map, significa que nao tem aquele RG cadastrado.
                }catch (Exception e){
                    flag = true;
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(TAG, "ONCACELLED");
            }
        });
        return flag;
    }
}
