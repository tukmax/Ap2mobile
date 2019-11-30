package com.example.weather;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.util.Log;
import android.widget.TextView;

import com.example.weather.DBHelper.DBHelper;
import com.example.weather.adapter.CityAdapter;
import com.example.weather.models.CityModel;
import com.example.weather.retrofit.WeatherService;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CityAdapter cityAdapter;
    private List<CityModel> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        cityAdapter = new CityAdapter(this, cities);
        recyclerView.setAdapter(cityAdapter);








        //banco de dados
        DBHelper dbh = new DBHelper(this); //chamando o dbhelper pra auxiliar na manipulacao do bd

        CityModel city = new CityModel("Recife"); //criando uma cidade
        CityModel city2 = new CityModel("Natal"); //criando uma cidade

        dbh.insertCity(city);// adicionando no banco
        dbh.insertCity(city2);// adicionando no banco

        // proximos estao criando uma lista e mostrando-as no log do logcat
        List<CityModel> lscity = dbh.cityAll(); // Retorna uma lista com todas as cidades cadastradas no banco
        for(Iterator iterator = lscity.iterator(); iterator.hasNext();){
            CityModel cidade = (CityModel) iterator.next();
            Log.i("myapp db", cidade.toString()); // mostra apenas no log do navcat

        }



        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService wservice = retrofit.create(WeatherService.class);


        //Chamada do retrofit para retornar os objetos
        Call<CityModel> call = wservice.getCidadePeloNome("sdfsdf", "afeb3f38cdff7769f0f18c09357e5750","metric");
        call.enqueue(new Callback<CityModel>() {
            @Override
            public void onResponse(Call<CityModel> call, Response<CityModel> response) {
                CityModel city = response.body();
                if(city != null){
                    //o que fazer quando encontra a cidade
                }else{
                    // o que fazer quando NAO encontra a cidade
                }

            }

            @Override
            public void onFailure(Call<CityModel> call, Throwable t) {
                Log.d("erro", "deu erro");
                //texto.setText("Algo de erraddo ocorreu desculpe");

            }
        });




    }
}
