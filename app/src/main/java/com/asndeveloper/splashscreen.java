package com.asndeveloper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.airbnb.lottie.LottieAnimationView;
import com.asndeveloper.asnweather.MainActivity;
import com.asndeveloper.asnweather.R;

public class splashscreen extends AppCompatActivity {
LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        lottieAnimationView=findViewById(R.id.splash);
        lottieAnimationView.setAnimation(R.raw.spl);
        lottieAnimationView.playAnimation();
  new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
          Intent inext=new Intent(splashscreen.this, MainActivity.class);
          startActivity(inext);
          finish();
      }
  },5000);


    }
}