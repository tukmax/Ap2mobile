package com.example.weather.retrofit;

import com.example.weather.models.CidadeModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherService {
    @GET("weather")
    Call<CidadeModel> getCidadePeloNome(@Query("q") String city,
                                @Query("APPID") String appid,
                                @Query("units") String unit);
}
