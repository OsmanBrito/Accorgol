package io.rerum.accorgol.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by osman on 10/10/2017.
 */

public class DbJogador extends SQLiteOpenHelper{

    private static final String NOME_BANCO = "jogador.db";
    private static final String TABELA = "jogadores";
    private static final String ID = "idJogador";
//    private static final String NOME = "nome";
//    private static final String EMAIL = "email";
//    private static final String CPF = "cpf";
//    //TODO ver a data de nascimento.
//    private static final String DATANASCIMENTO = "datanascimento";
//    private static final String ENDERECO = "endereco";
//    private static final String BAIRRO = "bairro";
//    private static final String CIDADE = "cidade";
//    private static final String ESTADO = "estado";
//    private static final String CEP = "cep";
//    private static final String CELULAR = "celular";
//    private static final String SENHA = "senha";
//    private static final String EMPRESA = "empresa";
//    private static final String REGISTRO = "registro";
    private static final int VERSAO = 1;



    public DbJogador(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+ TABELA+"("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+ TABELA);
        onCreate(db);
    }

}

