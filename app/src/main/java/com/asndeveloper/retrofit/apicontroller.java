package com.asndeveloper.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apicontroller {
    private static  final String url="https://api.openweathermap.org/";
    private static apicontroller apiobject;
    private  static Retrofit retrofit;

    apicontroller(){
        retrofit=new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

      public static synchronized apicontroller getInstance(){
        if (apiobject==null)
            apiobject=new apicontroller();
        return  apiobject;
      }
    public apiInterf getapi(){
        return retrofit.create(apiInterf.class);
    }
}
