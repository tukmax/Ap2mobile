package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.weather.DBHelper.ConexaoSQLite;
import com.example.weather.controller.CidadeCtrl;
import com.example.weather.models.CidadeModel;
import com.example.weather.retrofit.WeatherService;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainActivity extends AppCompatActivity {

    private  TextView texto;
    private ImageButton btnCadastrarCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Teste banco de dados
        ConexaoSQLite conexaoSQLite = ConexaoSQLite.getInstancia(this);
        CidadeModel cidade = new CidadeModel();
        cidade.setName("London");
        cidade.setCod(200);

        CidadeCtrl ctrl = new CidadeCtrl(conexaoSQLite);
        long resultado = ctrl.salvarCidadeCtrl(cidade);

        System.out.println("Banco funcionou = " + resultado);

        this.btnCadastrarCidade = (ImageButton) findViewById(R.id.btnAddCity);

        btnCadastrarCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CadastroCidadeActivity.class);
                startActivity(intent);
            }
        });

        this.texto = findViewById(R.id.editTextDebug);
        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService wservice = retrofit.create(WeatherService.class);


        //Chamada do retrofit para retornar os objetos
        Call<CidadeModel> call = wservice.getCidadePeloNome("recife", "afeb3f38cdff7769f0f18c09357e5750","metric");
        call.enqueue(new Callback<CidadeModel>() {
            @Override
            public void onResponse(Call<CidadeModel> call, Response<CidadeModel> response) {
                CidadeModel city = response.body();
                texto.setText(city.toString());
            }

            @Override
            public void onFailure(Call<CidadeModel> call, Throwable t) {
                System.out.println("Erro");
            }
        });




    }
}
