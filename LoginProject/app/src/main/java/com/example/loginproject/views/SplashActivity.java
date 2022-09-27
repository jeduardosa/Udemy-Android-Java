package com.example.loginproject.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import com.example.loginproject.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //getSupportActionBar().hide(); //Esconder barra superior (título do app, Action bar)

        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish(); //Destruir o Splash pq só vai ser executado uma vez
            }
        }).start();
    }
}