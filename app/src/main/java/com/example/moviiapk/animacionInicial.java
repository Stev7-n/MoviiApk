
package com.example.moviiapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class animacionInicial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animacion_inicial);

        LottieAnimationView animationView = findViewById(R.id.animaciontelefono);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(animacionInicial.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },1100);
    }
}
