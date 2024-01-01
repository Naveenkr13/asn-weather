package com.asndeveloper.asnweather.model;

import java.util.List;

public class MausamData_Main_Root {
    public List<weather> weather;
    public main main;
    public String name;

    public MausamData_Main_Root(List<com.asndeveloper.asnweather.model.weather> weather, com.asndeveloper.asnweather.model.main main, String name) {
        this.weather = weather;
        this.main = main;
        this.name = name;
    }

    public List<com.asndeveloper.asnweather.model.weather> getWeather() {
        return weather;
    }

    public void setWeather(List<com.asndeveloper.asnweather.model.weather> weather) {
        this.weather = weather;
    }

    public com.asndeveloper.asnweather.model.main getMain() {
        return main;
    }

    public void setMain(com.asndeveloper.asnweather.model.main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

