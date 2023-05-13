package com.example.moviiapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toolbar;

public class transferencias extends AppCompatActivity {

    private int cantDinero;
    private String numeroUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        cantDinero = getIntent().getIntExtra("cantDinero", 0);
        numeroUsuario = getIntent().getStringExtra("numeroUsuario");
    }

    public void transferir (View v) {
        Intent intent = new Intent(this, transferencias2.class);
        intent.putExtra("cantDinero", cantDinero);
        intent.putExtra("numeroUsuario", numeroUsuario);
        startActivity(intent);
    }
}