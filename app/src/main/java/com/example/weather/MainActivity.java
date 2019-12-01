package com.example.weather;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

    private EditText my_edittext;
    private Button my_button;
    private Button btnRemoveCity;

    private RecyclerView recyclerView;
    private CityAdapter cityAdapter;
    private List<CityModel> cities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //linkando o botao e o edittext
        my_edittext = (EditText) findViewById(R.id.my_edittext);
        my_button = (Button) findViewById(R.id.my_button);
        btnRemoveCity = (Button) findViewById(R.id.btnRemoveCity);

        //Botão para add cidades
        my_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String nome = my_edittext.getText().toString();
                    CityModel city = new CityModel(nome);
                    add_city(city);
                    cityAdapter.clear();
                    att();// atualiza o recycleview


            }
        });

        //Botão remover cidades
        btnRemoveCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = my_edittext.getText().toString();
                CityModel city = new CityModel(nome);
                rmv_city(city);
                cityAdapter.clear();
                att();// atualiza o recycleview


            }
        });


        //recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        cityAdapter = new CityAdapter(this, cities);
        recyclerView.setAdapter(cityAdapter);


        //popular banco de dados com 5 cidades se estiver vazio
        DBHelper dbh = new DBHelper(this);
        if(dbh.cityAll().isEmpty()){
            CityModel city = new CityModel("Recife"); //criando uma cidade
            CityModel city2 = new CityModel("Natal"); //criando uma cidade
            CityModel city3 = new CityModel("Caruaru"); //criando uma cidade
            CityModel city4 = new CityModel("Caracas"); //criando uma cidade
            CityModel city5 = new CityModel("Brasilia"); //criando uma cidade
            add_city(city);
            add_city(city2);
            add_city(city3);
            add_city(city4);
            add_city(city5);
        }





        //Método para remover todas as cidades cadastradas
        //dbh.removeAllCities();

        //Atualiza o recycleview
        att();




    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //limpar o recycleview de componentes duplicados
        cityAdapter.clear();
        //recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        cityAdapter = new CityAdapter(this, cities);
        recyclerView.setAdapter(cityAdapter);

        //atualizar o recycleview
        att();


    }

    public void add_city(CityModel obj){
        DBHelper dbh = new DBHelper(this);
        dbh.insertCity(obj);

    }
    public void rmv_city(CityModel obj){
        DBHelper dbh = new DBHelper(this);
        dbh.removeCity(obj);

    }

    public void att(){
        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService wservice = retrofit.create(WeatherService.class);

        //Instância do banco
        DBHelper dbh = new DBHelper(this);

        //Estrutura de repetição para listar as cidades cadastradas
        for(int c=0;c<dbh.cityAll().size();c++) {
            //Chamada do retrofit para retornar os objetos
            List<CityModel> lscity = dbh.cityAll();
            Call<CityModel> call = wservice.getCidadePeloNome(lscity.get(c).getName(), "afeb3f38cdff7769f0f18c09357e5750", "metric");
            call.enqueue(new Callback<CityModel>() {
                @Override
                public void onResponse(Call<CityModel> call, Response<CityModel> response) {
                    CityModel city = response.body();
                    if (city != null) {
                        //o que fazer quando encontra a cidade
                        cities.add(city);
                        cityAdapter.notifyDataSetChanged();
                        System.out.println("Cidade encontrada");
                    } else {
                        // o que fazer quando NAO encontra a cidade
                        System.out.println("Cidade não encontrada");
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
}
