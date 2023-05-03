package com.example.moviiapk;

import androidx.appcompat.app.AppCompatActivity;

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

        ingresaUL=findViewById(R.id.ingresaUsuario);
        ingresaCL=findViewById(R.id.ingresaContraseña);
    }

    public void iniciarSesion (View v) {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String ingresaUsuario = ingresaUL.getText().toString();
        String ingresaContraseña = ingresaCL.getText().toString();
        Cursor fila = bd.rawQuery("select numeroUsuario, contraseñaUsuario from usuario where numeroUsuario='" + ingresaUsuario +
                "' and contraseñaUsuario='" + ingresaContraseña + "'", null);

        if (fila.moveToFirst()) {
            Intent i = new Intent(this, inicioDeSesion.class);
            startActivity(i);
            finish();
        } else
            Toast.makeText(this, "Usuario y contraseña incorrectos",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

}