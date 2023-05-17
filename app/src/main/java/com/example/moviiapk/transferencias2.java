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

import java.text.SimpleDateFormat;
import java.util.Date;

public class transferencias2 extends AppCompatActivity {

    TextInputEditText numeroUsuarioTransferencia;
    TextInputEditText numeroConfirmarUsuarioTransferencia;
    TextInputEditText cantidadUsuarioTransferencia;

    private int cantDinero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transferencias2);

        numeroUsuarioTransferencia = findViewById(R.id.numeroTransferencia);
        numeroConfirmarUsuarioTransferencia = findViewById(R.id.confirmarNumeroT);
        cantidadUsuarioTransferencia = findViewById(R.id.cantidadDineroT);

        cantDinero = getIntent().getIntExtra("cantDinero", 0);

    }

    public void registrarTransferencia(View v) {
        String numeroUsuario = getIntent().getStringExtra("numeroUsuario");
        String numeroT = numeroUsuarioTransferencia.getText().toString();
        String confirmarT = numeroConfirmarUsuarioTransferencia.getText().toString();
        String cantidadT = cantidadUsuarioTransferencia.getText().toString();

        // validamos si el número de transferencia es igual al número del usuario actual y se cancela
        if (numeroT.equals(numeroUsuario)) {
            Toast.makeText(this, "No puedes transferir dinero a tu propia cuenta", Toast.LENGTH_SHORT).show();
            return;
        }

        //validar si el numero al que vamos a transferirle dinero esta en la base de datos, no sirve
        if (!verificarNumeroTransferencia(numeroT)) {
            Toast.makeText(this, "El número de transferencia no está registrado en la base de datos", Toast.LENGTH_SHORT).show();
            return;
        }

        //validacion para que los numeros de transferencia y confirmacion sean iguales
        if (!numeroT.equals(confirmarT)) {
            Toast.makeText(this, "Los números de transferencia no coinciden", Toast.LENGTH_SHORT).show();
            return;
        }

        int cantidadTransferencia = Integer.parseInt(cantidadT);
        //validar la cantidad de dinero que tengo, para ver si puedo hacer la transferencia o no
        if (cantDinero < cantidadTransferencia) {
            Toast.makeText(this, "No tienes suficiente dinero para realizar esta transferencia", Toast.LENGTH_SHORT).show();
            return;
        }

        //agregamos una condicion de queel monto minimo sea de 5mil
        if (cantidadTransferencia < 5000) {
            Toast.makeText(this, "La cantidad mínima de transferencias debe ser de 5000", Toast.LENGTH_SHORT).show();
            return;
        }

        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        ContentValues actualizarOrigen = new ContentValues();
        actualizarOrigen.put("cantDinero", cantDinero - cantidadTransferencia);
        int filasActualizadas = bd.update("usuario", actualizarOrigen,
                "numeroUsuario=?", new String[]{numeroUsuario});
        if (filasActualizadas != 1) {
            Toast.makeText(this, "Error al actualizar la cuenta del usuario", Toast.LENGTH_SHORT).show();
            bd.close();
            return;
        }

        ContentValues actualizarDestino = new ContentValues();
        actualizarDestino.put("cantDinero", getCantidadDinero(numeroT) + cantidadTransferencia);
        filasActualizadas = bd.update("usuario", actualizarDestino,
                "numeroUsuario=?", new String[]{numeroT});
        if (filasActualizadas != 1) {
            Toast.makeText(this, "Error al actualizar la cuenta del usuario destino", Toast.LENGTH_SHORT).show();
            bd.close();
            return;
        }

        cantDinero -= cantidadTransferencia;

        SimpleDateFormat dia = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat tiempo = new SimpleDateFormat("HH:mm:ss");
        String fecha = dia.format(new Date());
        String hora = tiempo.format(new Date());

        ContentValues registrar = new ContentValues();
        registrar.put("numeroTransferencia", numeroT);
        registrar.put("confirmarTransferencia", confirmarT);
        registrar.put("cantidadTransferencia", cantidadT);
        registrar.put("numeroUsuario", numeroUsuario);
        registrar.put("fechaTransferencia", fecha);
        registrar.put("horaTransferencia", hora);

        long resultado = bd.insert("transferencias", null, registrar);
        bd.close();

        if (resultado == -1) {
            Toast.makeText(this, "Error al hacer la transacción", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "La transacción ha sido exitosa", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, inicioDeSesion.class);
            intent.putExtra("numeroUsuario", numeroUsuario);
            intent.putExtra("cantDinero", cantDinero);
            startActivity(intent);
            finish();
        }
    }

    private boolean verificarNumeroTransferencia(String numeroTransferencia) {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String[] campos = {"numeroUsuario"};
        String[] argumentos = {numeroTransferencia};
        Cursor fila = bd.query("usuario", campos, "numeroUsuario=?", argumentos, null, null, null);

        boolean existe = fila.moveToFirst();

        fila.close();
        bd.close();

        return existe;
    }

    private int getCantidadDinero(String numeroUsuario) {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String[] campos = {"cantDinero"};
        String[] argumentos = {numeroUsuario};
        Cursor fila = bd.query("usuario", campos, "numeroUsuario=?", argumentos, null, null, null);

        int cantidadDinero = 0;
        if (fila.moveToFirst()) {
            cantidadDinero = fila.getInt(0);
        }

        fila.close();
        bd.close();

        return cantidadDinero;
    }

}