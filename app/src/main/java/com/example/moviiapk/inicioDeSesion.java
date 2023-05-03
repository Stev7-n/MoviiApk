package com.example.moviiapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class inicioDeSesion extends AppCompatActivity {

    TextView dinero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_de_sesion);

        dinero=findViewById(R.id.dinero);

        int x = 1000000;
        dinero.setText("Su dinero es "+ x);

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), transferencias.class);
                startActivity(intent);
            }
        });
    }
}