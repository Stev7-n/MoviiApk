package com.example.moviiapk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;

public class transferencias2 extends AppCompatActivity {

    TextInputEditText numeroUsuarioTransferencia;
    TextInputEditText numeroConfirmarUsuarioTransferencia;
    TextInputEditText cantidadUsuarioTransferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias2);

        numeroUsuarioTransferencia = findViewById(R.id.numeroTransferencia);
        numeroConfirmarUsuarioTransferencia = findViewById(R.id.confirmarNumeroT);
        cantidadUsuarioTransferencia = findViewById(R.id.cantidadDineroT);

        String numeroUsuario = getIntent().getStringExtra("numeroUsuario");
    }

    public void registrarTransferencia (View v) {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String numeroT = numeroUsuarioTransferencia.getText().toString();
        String confirmarT = numeroConfirmarUsuarioTransferencia.getText().toString();
        String cantidadT = cantidadUsuarioTransferencia.getText().toString();

        ContentValues registrar = new ContentValues();

        registrar.put("numeroTransferencia", numeroT);
        registrar.put("confirmarTransferencia", confirmarT);
        registrar.put("cantidadTransferencia", cantidadT);
        registrar.put("numeroUsuario", getIntent().getStringExtra("numeroUsuario"));

        long resultado = bd.insert("transferencias", null, registrar);
        bd.close();

        if (resultado == -1) {
            Toast.makeText(this, "Error al hacer la transaccion", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "La transaccion ha sido exitosa", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(this, inicioDeSesion.class);
            startActivity(i);

            finish();
        }
    }

}