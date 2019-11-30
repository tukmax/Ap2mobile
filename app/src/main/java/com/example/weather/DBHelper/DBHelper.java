package com.example.weather.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import com.example.weather.models.CityModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mydb.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createCity = "CREATE TABLE IF NOT EXISTS city (" +
                "cod_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "city_nome TEXT" +
                ")";
        db.execSQL(createCity);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertCity(CityModel city){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("city_nome", city.getName());
        db.insert("city", null, cv);
        db.close();
    }

    public List<CityModel> cityAll(){
        List<CityModel> listcity = new ArrayList<CityModel>();
        SQLiteDatabase db = getReadableDatabase();
        String sqlcityall = "SELECT * FROM city";
        Cursor cursor = db.rawQuery(sqlcityall,null);
        if(cursor.moveToFirst()){
            do{
                CityModel ct = new CityModel(cursor.getString(1)); //criando um objeto de citymodel
                //ct.setCity_name(cursor.getString(1)); // puchando do cursor um tipo string para o index da coluna da tabela referente ao city_name
                listcity.add(ct); // Adicionando a cidade a lista de citadaes
            }while(cursor.moveToNext());
        }
        db.close();
        return listcity;
    }


}
