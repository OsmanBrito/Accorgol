package io.rerum.accorgol.controller;

import android.content.Context;

import io.rerum.accorgol.model.Jogador;

/**
 * Created by osman on 10/10/2017.
 */

public class JogadorService {

    public void cadastrarJogador(Jogador jogador, Context ctx){
        FirebaseHelper firebaseHelper = new FirebaseHelper();
        firebaseHelper.verifyIdInDataBase("Jogadores", ctx, null, jogador);
    }


}
