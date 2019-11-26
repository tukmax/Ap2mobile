package com.example.weather.DAO;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.weather.DBHelper.ConexaoSQLite;
import com.example.weather.models.CidadeModel;

public class CidadeDAO {

    private ConexaoSQLite conexaoSQLite;

    public CidadeDAO(ConexaoSQLite conexaoSQLite){
        this.conexaoSQLite = conexaoSQLite;
    }

    public long salvarCidade(CidadeModel ccidade){
        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();

            values.put("cod",ccidade.getCod());
            values.put("name",ccidade.getName());

            long codCidadeInserida = db.insert("cidade",null,values);

            return codCidadeInserida;
        }catch(Exception e){
            e.printStackTrace();
        }
        return 0;
    }

}
