package com.example.moviiapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class login extends AppCompatActivity {

    private TextInputEditText ingresaUL;
    private TextInputEditText ingresaCL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ingresaUL = findViewById(R.id.ingresaUsuario);
        ingresaCL = findViewById(R.id.ingresaContraseña);
    }

    public void iniciarSesion(View v) {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ingresaUsuario = ingresaUL.getText().toString();
        String ingresaContraseña = ingresaCL.getText().toString();

        if (ingresaUsuario.isEmpty() || ingresaContraseña.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor fila = bd.rawQuery("SELECT numeroUsuario, contraseñaUsuario, cantDinero, ultimaActualizacion FROM usuario WHERE numeroUsuario='" + ingresaUsuario +
                "' AND contraseñaUsuario='" + ingresaContraseña + "'", null);

        if (fila.moveToFirst()) {
            int cantDinero = fila.getInt(2);
            long ultimaActualizacion = fila.getLong(3);

            long fechaActual = System.currentTimeMillis();

            long diferenciaTiempo = fechaActual - ultimaActualizacion;

            long diffDays = diferenciaTiempo / (24 * 60 * 60 * 1000);

            if (diffDays >= 1) {
                ContentValues actualizarDinero = new ContentValues();
                actualizarDinero.put("cantDinero", 1000000);
                bd.update("usuario", actualizarDinero, "numeroUsuario=?", new String[]{ingresaUsuario});

                ContentValues actualizarUltimaActualizacion = new ContentValues();
                actualizarUltimaActualizacion.put("ultimaActualizacion", fechaActual);
                bd.update("usuario", actualizarUltimaActualizacion, "numeroUsuario=?", new String[]{ingresaUsuario});

                cantDinero = 1000000;
            }

            Intent intent = new Intent(this, inicioDeSesion.class);
            intent.putExtra("numeroUsuario", ingresaUsuario);
            intent.putExtra("cantDinero", cantDinero);
            startActivity(intent);
            finish();

        } else {

            Toast.makeText(this, "Usuario y contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }

        bd.close();
    }

}