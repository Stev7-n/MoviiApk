package com.example.moviiapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

public class transferencias extends AppCompatActivity {

    EditText nombreUsuarioTR;
    EditText apellidoUsuarioRegistrado;
    EditText documentoUsuarioRegistrado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void transferir (View v) {

        EditText nombreUsuarioTR = findViewById(R.id.nombreUsuarioTR);
        EditText apellidoUsuarioRegistrado = findViewById(R.id.apellidoUsuarioRegistrado);
        EditText documentoUsuarioRegistrado = findViewById(R.id.documentoUsuarioRegistrado);

        if (nombreUsuarioTR.getText().toString().trim().equals("") ||
                apellidoUsuarioRegistrado.getText().toString().trim().equals("") ||
                documentoUsuarioRegistrado.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();

        } else {
            Intent intent = new Intent(this, transferencias2.class);
            startActivity(intent);
        }
    }
}