package com.example.weather.DBHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static ConexaoSQLite instancia_Conexao;
    private static final int versao_DB = 1;
    private static final String nome_DB = "Clima_Cidades";

    private ConexaoSQLite(Context context){
        super(context, nome_DB,null,versao_DB );
    }

    public static ConexaoSQLite getInstancia(Context context){
        if(instancia_Conexao == null){
            instancia_Conexao = new ConexaoSQLite(context);
        }
        return instancia_Conexao;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlTabelaCidades =
                "CREATE TABLE IF NOT EXISTS cidade" +
                "(" +
                "cod INTEGER PRIMARY KEY," +
                        "nome TEXT)";

        SQLiteDatabase.openOrCreateDatabase(sqlTabelaCidades,null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
