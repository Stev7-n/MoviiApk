package com.example.moviiapk;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

public class tarjetaVirtual extends AppCompatActivity {

    TextView txtcodigo;
    TextView txtNumeroUsuario;
    TextView txtCorreoUsuario;
    TextView txtCodigoSeguridad;
    TextView txtSaldoActual;
    String numeroUsuario;
    String correoUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarjeta_virtual);

        txtcodigo = findViewById(R.id.txtNumBin);
        txtNumeroUsuario = findViewById(R.id.textView5);
        txtCorreoUsuario = findViewById(R.id.textView6);
        txtCodigoSeguridad = findViewById(R.id.textView7);
        txtSaldoActual = findViewById(R.id.textView8);

        numeroUsuario = getIntent().getStringExtra("numeroUsuario");

        mostrarCodigo4();
        mostrarNumeroUsuario();
        mostrarCorreoUsuario();
        mostrarCodigoSeguridad();
        mostrarSaldoActual();
    }

    private void mostrarCodigo4() {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT codigo4 FROM usuario WHERE numeroUsuario = '" + numeroUsuario + "'", null);

        if (cursor.moveToFirst()) {
            String codigo4 = cursor.getString(0);
            String codigoCompleto = "**** **** **** " + codigo4;
            txtcodigo.setText(codigoCompleto);
        }

        cursor.close();
        db.close();
    }

    private void mostrarNumeroUsuario() {
        txtNumeroUsuario.setText("Número de Usuario: " + numeroUsuario);
    }

    private void mostrarCorreoUsuario() {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT correoUsuario FROM usuario WHERE numeroUsuario = '" + numeroUsuario + "'", null);

        if (cursor.moveToFirst()) {
            correoUsuario = cursor.getString(0);
            txtCorreoUsuario.setText("Correo Usuario: " + correoUsuario);
        }

        cursor.close();
        db.close();
    }

    private void mostrarCodigoSeguridad() {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT codigo10 FROM usuario WHERE numeroUsuario = '" + numeroUsuario + "'", null);

        if (cursor.moveToFirst()) {
            String codigoSeguridad = cursor.getString(0);
            txtCodigoSeguridad.setText("Código de Seguridad: " + codigoSeguridad);
        }

        cursor.close();
        db.close();
    }

    private void mostrarSaldoActual() {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT CantDinero FROM usuario WHERE numeroUsuario = '" + numeroUsuario + "'", null);

        if (cursor.moveToFirst()) {
            String saldoActual = cursor.getString(0);
            txtSaldoActual.setText("Saldo Actual: " + saldoActual);
        }

        cursor.close();
        db.close();
    }


}
