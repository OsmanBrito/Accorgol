package io.rerum.accorgol.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import io.rerum.accorgol.controller.BancoController;
import io.rerum.accorgol.model.Empresario;

/**
 * Created by osman on 28/09/2017.
 */

public class EmpresarioDAO {

    private final String TABLE_EMPRESARIOS = "empresarios";
    private BancoController bc;

    public EmpresarioDAO(Context ctx){
        bc = BancoController.getInstance(ctx);
    }

    public boolean SalvarCamposObrigatorios(String nomeCompleto, String email, String cpf, String dataDeNascimento, String endereco, String bairro, String cidade, String estado, Long cep, Long celular, String senha, String empresa, String numeroRegistro){
        ContentValues cv = new ContentValues();
        cv.put("nome", nomeCompleto);
        cv.put("email", email);
        cv.put("cpf", cpf);
        cv.put("datanascimento", dataDeNascimento);
        cv.put("endereco", endereco);
        cv.put("bairro", bairro);
        cv.put("cidade", cidade);
        cv.put("estado", estado);
        cv.put("cep", cep);
        cv.put("celular", celular);
        cv.put("senha", senha);
        cv.put("empresa", empresa);
        cv.put("registro", numeroRegistro);

        return bc.getDatabase().insert(TABLE_EMPRESARIOS, null, cv) > 0;
    }

    public int getIdEmpresario(){
        Cursor cursor = bc.getDatabase().rawQuery("SELECT * FROM  empresarios", null);

        int id = 0;

        while(cursor.moveToNext()){
            id = cursor.getInt(cursor.getColumnIndex("idEmpresario"));
        }
        cursor.close();

        return id;
    }

    public Empresario retornaEmpresario(){
        Empresario empresario = new Empresario();
        Cursor cursor = bc.getDatabase().rawQuery("SELECT * FROM empresarios", null);
        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("idEmpresario"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String email = cursor.getString(cursor.getColumnIndex("email"));
            String cpf = cursor.getString(cursor.getColumnIndex("cpf"));
            String dataNascimento = cursor.getString(cursor.getColumnIndex("datanascimento"));
            String endereco = cursor.getString(cursor.getColumnIndex("endereco"));
            String bairro = cursor.getString(cursor.getColumnIndex("bairro"));
            String cidade = cursor.getString(cursor.getColumnIndex("cidade"));
            String estado = cursor.getString(cursor.getColumnIndex("estado"));
            String cep = cursor.getString(cursor.getColumnIndex("cep"));
            String celular = cursor.getString(cursor.getColumnIndex("celular"));
            String empresa = cursor.getString(cursor.getColumnIndex("empresa"));
            String registro = cursor.getString(cursor.getColumnIndex("registro"));

            empresario = new Empresario(nome, email, cpf, dataNascimento, endereco, bairro, cidade, estado, new Long(cep), new Long(celular), empresa, registro);
        }

        cursor.close();
        Log.e("COEEE", empresario.getEmail());
        return empresario;
    }

    public boolean alterarEmpresario(Empresario empresario){

        ContentValues cv = new ContentValues();
        cv.put("nome", empresario.getNomeCompleto());
        cv.put("email", empresario.getEmail());
        cv.put("datanascimento", empresario.getData());
        cv.put("endereco", empresario.getEndereco());
        cv.put("bairro", empresario.getBairro());
        cv.put("cidade", empresario.getCidade());
        cv.put("estado", empresario.getEstado());
        cv.put("cep", empresario.getCep());
        cv.put("celular", empresario.getCelular());
        cv.put("empresa", empresario.getEmpresa());
        cv.put("registro", empresario.getNumeroRegistro());

        return bc.getDatabase().update(TABLE_EMPRESARIOS, cv, "idEmpresario = ?", new String[]{ this.getIdEmpresario() +  ""}) > 0;

    }


}
