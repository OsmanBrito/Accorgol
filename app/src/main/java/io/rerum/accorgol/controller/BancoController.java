package io.rerum.accorgol.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import io.rerum.accorgol.dao.DbUsuario;

/**
 * Created by osman on 28/09/2017.
 */

public class BancoController {

    private static BancoController gw;
    private SQLiteDatabase db;

    private BancoController(Context ctx){
        DbUsuario helper = new DbUsuario(ctx);
        db = helper.getWritableDatabase();
    }


    //singleton
    public static BancoController getInstance(Context ctx){
        if(gw == null)
            gw = new BancoController(ctx);
        return gw;
    }

    public SQLiteDatabase getDatabase(){
        return this.db;
    }

}
