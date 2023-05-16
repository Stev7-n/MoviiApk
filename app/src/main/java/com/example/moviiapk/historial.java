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
    List<claseConstructor2> transferenciasRecibidas;
    ListAdapter listAdapter;
    ListAdapter2 transferenciasRecibidasAdapter;
    RecyclerView recyclerView;
    RecyclerView recyclerViewTransferenciasRecibidas;

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
        transferenciasRecibidas = new ArrayList<>();

        consulta();
        obtenerTransferenciasEnviadas();

        listAdapter = new ListAdapter(elementos, this);
        transferenciasRecibidasAdapter = new ListAdapter2(transferenciasRecibidas, this);

        recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);

        recyclerViewTransferenciasRecibidas = findViewById(R.id.listRecyclerView2);
        recyclerViewTransferenciasRecibidas.setHasFixedSize(true);
        recyclerViewTransferenciasRecibidas.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTransferenciasRecibidas.setAdapter(transferenciasRecibidasAdapter);
    }

    private void consulta() {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        try (Cursor cursor = db.rawQuery("SELECT numeroTransferencia, cantidadTransferencia, fechaTransferencia, horaTransferencia FROM transferencias WHERE numeroUsuario = '" + numeroUsuario + "'", null);) {
            if (!cursor.moveToFirst()) {
                Toast.makeText(this, "No se encontraron transacciones para este usuario", Toast.LENGTH_SHORT).show();
                return;
            }
            do {
                clasecontructor usuario = new clasecontructor();
                usuario.setNumerotransferenia(cursor.getString(0));
                usuario.setCantidadtransferencia(cursor.getString(1));
                usuario.setFechatransferencia(cursor.getString(2));
                usuario.setHoratransfernecia(cursor.getString(3));
                elementos.add(usuario);
            } while (cursor.moveToNext());
        }
        db.close();
    }

    private void obtenerTransferenciasEnviadas() {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        try (Cursor cursor = db.rawQuery("SELECT numeroUsuario, cantidadTransferencia, fechaTransferencia, horaTransferencia FROM transferencias WHERE numeroTransferencia = '" + numeroUsuario + "'", null);) {
            if (!cursor.moveToFirst()) {
                Toast.makeText(this, "No se encontraron transferencias recibidas para este usuario", Toast.LENGTH_SHORT).show();
                return;
            }
            do {
                claseConstructor2 transferenciaRecibida = new claseConstructor2();
                transferenciaRecibida.setnumeroQEnvio(cursor.getString(0));
                transferenciaRecibida.setcantidadQRecibio(cursor.getString(1));
                transferenciaRecibida.setfechaQTrans(cursor.getString(2));
                transferenciaRecibida.sethoraQTrans(cursor.getString(3));
                transferenciasRecibidas.add(transferenciaRecibida);
            } while (cursor.moveToNext());
        }
        db.close();
    }

}


