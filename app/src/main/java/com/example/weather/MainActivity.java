package com.example.weather;

<<<<<<< HEAD

=======
import androidx.annotation.NonNull;
>>>>>>> ca9dcf7b63ada5a741ec8547fee2c1bab01da486
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        //Teste banco de dados
        ConexaoSQLite conexaoSQLite = ConexaoSQLite.getInstancia(this);
        CidadeModel cidade = new CidadeModel();
        cidade.setName("London");
        cidade.setCod(200);

        CidadeCtrl ctrl = new CidadeCtrl(conexaoSQLite);
        long resultado = ctrl.salvarCidadeCtrl(cidade);

        System.out.println("Banco funcionou = " + resultado);

        this.btnCadastrarCidade = (ImageButton) findViewById(R.id.btnAddCity);
=======
>>>>>>> parent of 2490bf2... Layout Basico e nova activity

        this.texto = findViewById(R.id.texto);
        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService wservice = retrofit.create(WeatherService.class);


        //Chamada do retrofit para retornar os objetos
        Call<CidadeModel> call = wservice.getCidadePeloNome("sdfsdf", "afeb3f38cdff7769f0f18c09357e5750","metric");
        call.enqueue(new Callback<CidadeModel>() {
            @Override
            public void onResponse(Call<CidadeModel> call, Response<CidadeModel> response) {
                CidadeModel city = response.body();
<<<<<<< HEAD
                if(city != null){

                    texto.setText(city.toString());
                }else{
                    texto.setText("Cidade não encontrada.");
                }
=======
                texto.setText(city.toString());
>>>>>>> ca9dcf7b63ada5a741ec8547fee2c1bab01da486


            }

            @Override
            public void onFailure(Call<CidadeModel> call, Throwable t) {
<<<<<<< HEAD
                Log.d("erro", "deu erro");
                //texto.setText("Algo de erraddo ocorreu desculpe");
=======
                texto.setText("Algo de erraddo ocorreu desculpe");
>>>>>>> ca9dcf7b63ada5a741ec8547fee2c1bab01da486
            }
        });




    }
}
