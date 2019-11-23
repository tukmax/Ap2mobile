package com.example.weather.retrofit;

import retrofit2.http.GET;

public interface WeatherService {
    @GET("weather")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
