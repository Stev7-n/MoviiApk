package com.example.moviiapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void iniciar (View v) {
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }

    public void registrar (View v) {
        Intent intent = new Intent(this, registro.class);
        startActivity(intent);
    }
}