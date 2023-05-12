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
import java.util.Date;

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

    }

    public void registrarTransferencia(View v) {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numeroUsuario = getIntent().getStringExtra("numeroUsuario");

        String numeroT = numeroUsuarioTransferencia.getText().toString();
        String confirmarT = numeroConfirmarUsuarioTransferencia.getText().toString();
        String cantidadT = cantidadUsuarioTransferencia.getText().toString();

        SimpleDateFormat dia = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tiempo = new SimpleDateFormat("HH:mm:ss");
        String fecha = dia.format(new Date());
        String hora = tiempo.format(new Date());

        ContentValues registrar = new ContentValues();

        registrar.put("numeroTransferencia", numeroT);
        registrar.put("confirmarTransferencia", confirmarT);
        registrar.put("cantidadTransferencia", cantidadT);
        registrar.put("numeroUsuario", getIntent().getStringExtra("numeroUsuario"));
        registrar.put("fechaTransferencia", fecha);
        registrar.put("horaTransferencia", hora);

        long resultado = bd.insert("transferencias", null, registrar);
        bd.close();

        if (resultado == -1) {
            Toast.makeText(this, "Error al hacer la transaccion", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "La transaccion ha sido exitosa", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, inicioDeSesion.class);
            intent.putExtra("numeroUsuario", getIntent().getStringExtra("numeroUsuario"));
            startActivity(intent);

            finish();
        }
    }

}