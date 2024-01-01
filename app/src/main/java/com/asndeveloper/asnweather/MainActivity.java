package com.asndeveloper.asnweather;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.asndeveloper.asnweather.model.MausamData_Main_Root;
import com.asndeveloper.asnweather.model.main;
import com.asndeveloper.asnweather.model.weather;
import com.asndeveloper.retrofit.apicontroller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    TextView date,Cityname,pressure,humidity,maxTemp ,minTemp,MainTemp,msg;
    Button btnsearch;
    EditText edtSearch;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout=findViewById(R.id.relative);
        linearLayout=findViewById(R.id.edlinear);
        date=findViewById(R.id.date);
        Cityname=findViewById(R.id.citynames);
        pressure=findViewById(R.id.pressure);
        humidity=findViewById(R.id.humadity);
        maxTemp=findViewById(R.id.maxtemp);
        minTemp=findViewById(R.id.mintemp);
        MainTemp=findViewById(R.id.maintemp);
        msg=findViewById(R.id.msgs);
        btnsearch=findViewById(R.id.btn);
        edtSearch=findViewById(R.id.search_cityname);

        SimpleDateFormat format=new SimpleDateFormat("dd MMM yyyy");
        String currentDate=format.format(new Date());
        date.setText(currentDate);
        //method call
        fetchweather("Gaya");

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //method call
                hidekeyboard();

                //conditon check if txt box empty or not
                if (TextUtils.isEmpty(edtSearch.getText().toString())){
                    edtSearch.setError("Enter City");
                    return;
                }
                String City_Name=edtSearch.getText().toString();
                fetchweather(City_Name);
            }
        });
    }

    private void  hidekeyboard(){
        InputMethodManager inputMethodManager=(InputMethodManager)
                getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(edtSearch.getApplicationWindowToken(),0);
    }

    private void fetchweather(String CityName){
        //create call for model and retrofit apicontroller
        Call<MausamData_Main_Root> call= apicontroller.getInstance().getapi().getData(CityName,
                "d8ee3484bd3c9e1425ae08fa51cf493f",
                        "metric");

        //enqueue method
        call.enqueue(new Callback<MausamData_Main_Root>() {
            @Override
            public void onResponse(Call<MausamData_Main_Root> call, Response<MausamData_Main_Root> response) {
                if (response.isSuccessful()){
                    MausamData_Main_Root mausamDataMainRoot=response.body();
                    //main model class call
                    main main=mausamDataMainRoot.getMain();

                    //show txtview
                    MainTemp.setText(String.valueOf(main.getTemp())+"\u2103");
                    maxTemp.setText(String.valueOf(main.getTemp_max()));
                    minTemp.setText(String.valueOf(main.getTemp_min()));
                    pressure.setText(String.valueOf(main.getPressure()));
                    humidity.setText(String.valueOf(main.getHumidity()));
                    //city name
                    Cityname.setText(mausamDataMainRoot.getName());
                    //make list for weather model class store desc and more
                    List<weather> description=mausamDataMainRoot.getWeather();
                    //for each loop uses  for desc haze,, clody etc
                    for (weather data:description){
                        msg.setText(data.getDescription());
                    }


                }
            }

            @Override
            public void onFailure(Call<MausamData_Main_Root> call, Throwable t) {
                Log.e("WeatherFetchError", "Error fetching weather data", t);
            }
        });







    }


}