package io.rerum.accorgol.dao;

import android.content.ContentValues;
import android.database.Cursor;

import io.rerum.accorgol.controller.BancoController;

/**
 * Created by osman on 10/10/2017.
 */

public class JogadorDAO {

    private final String TABLE = "jogadores";
    private BancoController bc;

    public boolean SalvarIdJogador(int id){
        ContentValues cv = new ContentValues();
        cv.put("nome", id);

        return bc.getDatabase().insert(TABLE, null, cv) > 0;
    }

    public int getIdJogador(){
        Cursor cursor = bc.getDatabase().rawQuery("SELECT * FROM "+TABLE, null);

        int id = 0;

        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex("idJogador"));
        }
        cursor.close();

        return id;
    }


}
