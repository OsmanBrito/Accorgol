package io.rerum.accorgol.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.io.Serializable;

import io.rerum.accorgol.controller.BancoController;

/**
 * Created by osman on 28/09/2017.
 */

public class UsuarioDAO {

    private final String TABLE_EMPRESARIOS = "usuario";
    private BancoController bc;

    public UsuarioDAO(Context ctx){
        bc = BancoController.getInstance(ctx);
    }

    public boolean salvarID(int id){
        ContentValues cv = new ContentValues();
        Log.e("USUARIODAO", String.valueOf(id));
        cv.put("idUsuario", id);
        return bc.getDatabase().insert(TABLE_EMPRESARIOS, null, cv) > 0;

    }

    public int getIDBanco(){
        Cursor cursor = bc.getDatabase().rawQuery("SELECT * FROM  usuario", null);
        int id = 0;
        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex("idUsuario"));
            Log.e("USUARIO dentro while", String.valueOf(id));
        }
        cursor.close();
        Log.i("USUARIOGETID", String.valueOf(id));
        return id;
    }

    public boolean attVideoUsuario(boolean video){
        ContentValues cv = new ContentValues();
        if (video){
            cv.put("video", 1);
        } else {
            cv.put("video", 0);
        }
        return bc.getDatabase().insert(TABLE_EMPRESARIOS, null, cv) > 0;
    }

    public boolean attFotoUsuario(boolean foto){
        ContentValues cv = new ContentValues();
        if (foto){
            cv.put("foto", 1);
        } else {
            cv.put("foto", 0);
        }
        return bc.getDatabase().insert(TABLE_EMPRESARIOS, null, cv) > 0;
    }

    public int hasVideo(){
        Cursor cursor = bc.getDatabase().rawQuery("SELECT * FROM  usuario", null);
        int id = 0;
        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex("video"));
        }
        cursor.close();
        return id;
    }

    public int hasFoto() {
        Cursor cursor = bc.getDatabase().rawQuery("SELECT * FROM  usuario", null);
        int id = 0;
        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex("foto"));
        }
        cursor.close();
        return id;
    }
//    public Empresario retornaEmpresario(){
//        Empresario empresario = new Empresario();
//        Cursor cursor = bc.getDatabase().rawQuery("SELECT * FROM empresarios", null);
//        while(cursor.moveToNext()){
//            int id = cursor.getInt(cursor.getColumnIndex("idEmpresario"));
//            String nome = cursor.getString(cursor.getColumnIndex("nome"));
//            String email = cursor.getString(cursor.getColumnIndex("email"));
//            String cpf = cursor.getString(cursor.getColumnIndex("cpf"));
//            String dataNascimento = cursor.getString(cursor.getColumnIndex("datanascimento"));
//            String endereco = cursor.getString(cursor.getColumnIndex("endereco"));
//            String bairro = cursor.getString(cursor.getColumnIndex("bairro"));
//            String cidade = cursor.getString(cursor.getColumnIndex("cidade"));
//            String estado = cursor.getString(cursor.getColumnIndex("estado"));
//            String cep = cursor.getString(cursor.getColumnIndex("cep"));
//            String celular = cursor.getString(cursor.getColumnIndex("celular"));
//            String empresa = cursor.getString(cursor.getColumnIndex("empresa"));
//            String registro = cursor.getString(cursor.getColumnIndex("registro"));
//
//            empresario = new Empresario(nome, email, cpf, dataNascimento, endereco, bairro, cidade, estado, cep, celular, empresa, registro);
//        }
//
//        cursor.close();
//        Log.e("COEEE", empresario.getEmail());
//        return empresario;
//    }

//    public boolean alterarEmpresario(Empresario empresario){
//
//        ContentValues cv = new ContentValues();
//        cv.put("nome", empresario.getNomeCompleto());
//        cv.put("email", empresario.getEmail());
//        cv.put("datanascimento", empresario.getDataDeNascimento());
//        cv.put("endereco", empresario.getEndereco());
//        cv.put("bairro", empresario.getBairro());
//        cv.put("cidade", empresario.getCidade());
//        cv.put("estado", empresario.getEstado());
//        cv.put("cep", empresario.getCep());
//        cv.put("celular", empresario.getCelular());
//        cv.put("empresa", empresario.getEmpresa());
//        cv.put("registro", empresario.getNumeroRegistro());
//
//        return bc.getDatabase().update(TABLE_EMPRESARIOS, cv, "idEmpresario = ?", new String[]{ this.getIdUsuario1() +  ""}) > 0;
//
//    }

}

