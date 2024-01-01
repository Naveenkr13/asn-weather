package com.asndeveloper.retrofit;

import com.asndeveloper.asnweather.model.MausamData_Main_Root;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apiInterf {

    @GET("data/2.5/weather")
    Call<MausamData_Main_Root> getData(
            @Query("q") String q,
            @Query("appid")String APIKEY,
            @Query("units")String units

    );

}
