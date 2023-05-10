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

import java.util.Random;

public class registro extends AppCompatActivity {

    public static String CantDinero = "1000000";
    TextInputEditText nombreUsuario;
    TextInputEditText correoUsuario;
    TextInputEditText numeroUsuario;
    TextInputEditText passwoardUsuario;
    TextInputEditText confirmarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombreUsuario = findViewById(R.id.nombreUsuarioRegistrado);
        correoUsuario = findViewById(R.id.correoUsuarioRegistrado);
        numeroUsuario = findViewById(R.id.numeroUsuarioRegistrado);
        passwoardUsuario = findViewById(R.id.contraseñaUsuarioRegistrado);
        confirmarUsuario = findViewById(R.id.confirmarUsuarioRegistrado);
    }

    public void registrarUsuario(View v) {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String numeroU = numeroUsuario.getText().toString();
        String passwoardU = passwoardUsuario.getText().toString();
        String confirmarU = confirmarUsuario.getText().toString();
        String correoU = correoUsuario.getText().toString();
        String nombreU = nombreUsuario.getText().toString();
        String dineroU = CantDinero.getBytes().toString();

        if (numeroU.isEmpty() || passwoardU.isEmpty() || confirmarU.isEmpty() || correoU.isEmpty() || nombreU.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        Cursor fila = bd.rawQuery("select * from usuario where numeroUsuario='" + numeroU + "'", null);
        if (fila.moveToFirst()) {
            Toast.makeText(this, "El número de usuario ya existe", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues registrar = new ContentValues();

        registrar.put("numeroUsuario", numeroU);
        registrar.put("contraseñaUsuario", passwoardU);
        registrar.put("confirmarUsuario", confirmarU);
        registrar.put("correoUsuario", correoU);
        registrar.put("nombreUsuario", nombreU);
        registrar.put("CantDinero", dineroU);

        long resultado = bd.insert("usuario", null, registrar);
        bd.close();

        if (resultado == -1) {
            Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "El registro ha sido exitoso", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, login.class);
            startActivity(i);

            finish();
        }
    }
}