package com.example.moviiapk;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class historial extends AppCompatActivity {

    String numeroUsuario;
    List<clasecontructor> elementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial);
        Bundle bundle = getIntent().getExtras();
        numeroUsuario = bundle.getString("numeroUsuario");
        init();
    }

    public void init() {

        elementos = new ArrayList<>();
        consulta();
        ListAdapter listAdapter = new ListAdapter(elementos, this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }


        private void consulta() {

            BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();

            try (Cursor fila = db.rawQuery("SELECT numeroTransferencia, cantidadTransferencia, fechaTransferencia, horaTransferencia FROM transferencias WHERE numeroUsuario = '" + numeroUsuario + "'", null);) {

                if (!fila.moveToFirst()) {
                    Toast.makeText(this, "No se encontraron transacciones para este usuario", Toast.LENGTH_SHORT).show();
                    return;
                }
                do {
                    clasecontructor usuario = new clasecontructor();
                    usuario.setNumerotransferenia(fila.getString(0));
                    usuario.setCantidadtransferencia(fila.getString(1));
                    usuario.setFechatransferencia(fila.getString(2));
                    usuario.setHoratransfernecia(fila.getString(3));
                    elementos.add(usuario);

                } while (fila.moveToNext());

            }
            db.close();
        }
}

