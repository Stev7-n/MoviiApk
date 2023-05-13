package com.example.moviiapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class inicioDeSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_de_sesion);


        String numeroUsuario = getIntent().getStringExtra("numeroUsuario");
        int cantDinero = getIntent().getIntExtra("cantDinero", 0);

        TextView dinero = findViewById(R.id.dinero);
        dinero.setText("Tu dinero es: " + cantDinero);

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), transferencias.class);
                intent.putExtra("numeroUsuario", numeroUsuario);
                intent.putExtra("cantDinero", cantDinero);
                startActivity(intent);
            }
        });

        ImageButton imageButton2 = findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), historial.class);
                intent.putExtra("numeroUsuario", numeroUsuario);
                intent.putExtra("cantDinero", cantDinero);
                startActivity(intent);
            }
        });

        ImageButton imageButton3 = findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), tarjetaVirtual.class);
                startActivity(intent);
            }
        });
    }
}