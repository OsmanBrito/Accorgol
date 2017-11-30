package io.rerum.accorgol.view.empresario;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.model.Empresario;

/**
 * Created by osman on 30/11/2017.
 */

public class EditOportunidade extends AppCompatActivity {

    private Spinner posicao;
    private Spinner pe;
    private Spinner altura;
    private EditText ano;
    private Spinner estado;
    private EditText cidade;
    private String id;
    private BootstrapButton botao;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionaroportunidade);

        ctx = getApplicationContext();
        posicao = (Spinner) findViewById(R.id.addOportunidadePosicao);
        pe = (Spinner) findViewById(R.id.addOportunidadePe);
        altura = (Spinner) findViewById(R.id.addOportunidadeAltura);
        ano = (EditText) findViewById(R.id.addOportunidadeAno);
        cidade = (EditText) findViewById(R.id.addOportunidadeCidade);
        estado = (Spinner) findViewById(R.id.addOportunidadeEstado);
        botao = (BootstrapButton) findViewById(R.id.addOportunidadeButton);
        Intent intent = getIntent();
        id = intent.getStringExtra("idOportunidade");

        buscarOportunidade();
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReferenceFromUrl("https://accorgol-5000e.firebaseio.com/Empresarios/"+new FirebaseHelper().recuperar(ctx, String.valueOf(R.string.id_Usuario))+"/Oportunidades/"+id);

                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        Log.e("COEEEE", myRef.toString());

                        myRef.child("cidade").setValue(cidade.getText().toString());
                        myRef.child("anoNascimento").setValue(ano.getText().toString());
                        myRef.child("estado").setValue(estado.getSelectedItem().toString());
                        myRef.child("posicao").setValue(posicao.getSelectedItem().toString());
                        myRef.child("peDominante").setValue(pe.getSelectedItem().toString());
                        myRef.child("alturaMin").setValue(altura.getSelectedItem().toString());
                        finish();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }


    public void buscarOportunidade(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("/Empresarios/"+new FirebaseHelper().recuperar(this, String.valueOf(R.string.id_Usuario)) + "/Oportunidades/"+id);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("COEEEE", "buscar empresario Ondatachange");
                posicao.setSelection(getPosicaoArray(dataSnapshot.child("posicao").getValue(String.class), R.array.posicao));
                altura.setSelection(getPosicaoArray(dataSnapshot.child("alturaMin").getValue(String.class), R.array.altura));
                estado.setSelection(getPosicaoArray(dataSnapshot.child("posicao").getValue(String.class), R.array.estadosBR));
                pe.setSelection(getPosicaoArray(dataSnapshot.child("peDominante").getValue(String.class), R.array.pe));
                ano.setText(dataSnapshot.child("anoNascimento").getValue(String.class));
                cidade.setText(dataSnapshot.child("cidade").getValue(String.class));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //ira retornar qual a posição que deve ser mostrado no spinner
    private int getPosicaoArray(String value, int array){
        String[] cRaces = getResources().getStringArray(array);
        int j = 0;
        for (String i : cRaces){
            if (i.equals(value)) {
                Log.e("USUARIOOOOOOO VER O J!!!", String.valueOf(j));
                return j;
            }
            j++;
        }
        return 0;
    }

    public void VoltarParaPerfilEmpresario(View view) {

        this.finish();

    }
}
