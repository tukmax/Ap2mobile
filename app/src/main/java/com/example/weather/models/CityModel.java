package com.example.weather.models;

public class CityModel {
    private String name;
    private Main main;

    public CityModel(String name) {
        this.name = name;
    }
    public CityModel(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "CityModel{" +
                "name='" + name +
                '}';
    }
}
