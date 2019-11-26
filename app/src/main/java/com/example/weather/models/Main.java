package com.example.weather.models;

public class Main {
    private Double temp, temp_min, temp_max, pressure, humidity;

    public Double getTemp() { return temp; }

    public void setTemp(Double temp) { this.temp = temp; }

    public Double getTemp_min() { return temp_min; }

    public void setTemp_min(Double temp_min) { this.temp_min = temp_min; }

    public Double getTemp_max() { return temp_max; }

    public void setTemp_max(Double temp_max) { this.temp_max = temp_max; }

    public Double getPressure() { return pressure; }

    public void setPressure(Double pressure) { this.pressure = pressure; }

    public Double getHumidity() { return humidity; }

    public void setHumidity(Double humidity) { this.humidity = humidity; }

    @Override
    public String toString() {
        return "{" +
                "temp=" + temp +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                '}';
    }
}
