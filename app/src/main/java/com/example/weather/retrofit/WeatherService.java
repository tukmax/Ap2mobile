package com.example.weather.retrofit;

import com.example.weather.models.CidadeModel;

import retrofit2.Call;
import retrofit2.http.GET;


public interface WeatherService {
    @GET("weather")
    Call<CidadeModel> getCidade(String user);
}
