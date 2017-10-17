package io.rerum.accorgol.controller;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.rerum.accorgol.model.Empresario;
import io.rerum.accorgol.model.Jogador;

/**
 * Created by osman on 10/10/2017.
 */

public class JogadorService {

    private int idEmpresario;
    private DatabaseReference mDatabase;

    public boolean cadastrarJogador(String nome, String email, String RG,String senha, String posicao, String anoNascimento, String peDominante, Context ctx){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Jogador jogador = new Jogador(email, senha, nome, RG,posicao, peDominante, anoNascimento);

        Helper helper = new Helper();
        helper.verifyIdInDataBase("Jogadores", ctx, null, jogador);

        return true;
    }

}
