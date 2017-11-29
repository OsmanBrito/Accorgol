package io.rerum.accorgol.controller;

import android.content.Context;

import io.rerum.accorgol.model.Jogador;

/**
 * Created by osman on 10/10/2017.
 */

public class JogadorService {

    public void cadastrarJogador(String nome, String email, String RG,String senha, String posicao, String anoNascimento, String peDominante, Context ctx){
        Jogador jogador = new Jogador(email, senha, nome, RG,posicao, peDominante, anoNascimento);
        FirebaseHelper firebaseHelper = new FirebaseHelper();
        firebaseHelper.verifyIdInDataBase("Jogadores", ctx, null, jogador);
    }


}
