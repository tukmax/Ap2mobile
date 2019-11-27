package com.example.weather.models;

import androidx.room.Entity;

@Entity
public class CidadeModel {
    private int cod;
    private String name;
    private Main main;

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

    public Main getMain() { return main; }

    public void setMain(Main main) { this.main = main; }

    @Override
    public String toString() {
        return "{" +
                "cod=" + cod +
                ", name='" + name + '\'' +
                ", main=" + main +
                '}';
    }
}
