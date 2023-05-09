package com.example.moviiapk;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class historial extends AppCompatActivity {

    private LinearLayout linearLayout;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        linearLayout = findViewById(R.id.linear_layout);

        BaseDeDatos baseDeDatos = new BaseDeDatos(this, "admin", null, 1);

        Cursor cursor = baseDeDatos.getTransferencias();

        while (cursor.moveToNext()) {
            String numeroTransferencia = cursor.getString(0);
            String confirmarTransferencia = cursor.getString(1);
            String cantidadTransferencia = cursor.getString(2);
            String fecha = cursor.getString(3);
            String hora = cursor.getString(4);

            TextView numeroTransferido = new TextView(this);
            numeroTransferido.setText("Número de la cuenta: " + numeroTransferencia);
            linearLayout.addView(numeroTransferido);

            TextView transferenciaMonto = new TextView(this);
            transferenciaMonto.setText("Valor transferido: " + cantidadTransferencia);
            linearLayout.addView(transferenciaMonto);

            TextView fechaExacta = new TextView(this);
            fechaExacta.setText("Fecha de la transferencia: " + fecha);
            linearLayout.addView(fechaExacta);

            TextView horaExacta = new TextView(this);
            horaExacta.setText("Hora de la transferencia: " + hora);
            linearLayout.addView(horaExacta);

            TextView espacio2 = new TextView(this);
            espacio2.setText("------------------------");
            linearLayout.addView(espacio2);
        }

        cursor.close();

    }
}
