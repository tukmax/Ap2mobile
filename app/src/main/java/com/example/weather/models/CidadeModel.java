package com.example.weather.models;

import androidx.room.Entity;

@Entity
public class CidadeModel {
    private int cod;
    private String name;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CidadeModel{" +
                "cod=" + cod +
                ", name='" + name + '\'' +
                '}';
    }
}
