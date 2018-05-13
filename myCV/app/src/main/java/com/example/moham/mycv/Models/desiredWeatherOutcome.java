package com.example.moham.mycv.Models;

public class desiredWeatherOutcome {
    String temp;
    String region;
    String date;

    public desiredWeatherOutcome(String temp, String region, String date) {
        this.temp = temp;
        this.region = region;
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
