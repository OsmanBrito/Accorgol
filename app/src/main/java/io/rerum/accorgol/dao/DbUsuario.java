package io.rerum.accorgol.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

/**
 * Created by osman on 28/09/2017.
 */

public class DbUsuario extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "banco.db";
    private static final String TABELA = "usuario";
    private static final String ID = "idUsuario";
    private static final String  FOTO = "foto";
    private static final String  VIDEO = "video";
    private static final int VERSAO = 5;



    public DbUsuario(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+ TABELA+"("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FOTO + " INTEGER, "
                + VIDEO + " INTEGER"
                + ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+ TABELA);
        onCreate(db);
    }



}
