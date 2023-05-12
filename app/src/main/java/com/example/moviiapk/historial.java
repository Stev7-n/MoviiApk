package com.example.moviiapk;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class historial extends AppCompatActivity {

    List<clasecontructor> elementos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etiqueta);
        Bundle bundle = getIntent().getExtras();
        String numeroUsuario = bundle.getString("numeroUsuario");


        init();
    }

    public void init(){
        elementos = new ArrayList<>();
        BaseDeDatos admin = new BaseDeDatos(this,"admin",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String numeroUsuario = getIntent().getStringExtra("numeroUsuario");
        Cursor fila = db.rawQuery("select numeroTransferencia,cantidadTransferencia," +
                "fechaTransferencia,horaTransferencia from transferencias where numeroUsuario='"+numeroUsuario+"'", null);
        for (int i = 0;i<fila.getCount();){
            if(fila.moveToNext()){
                elementos.add(new clasecontructor(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3)));
            }
            i++;
        }if (elementos!= null) {
            ListAdapter listAdapter = new ListAdapter(elementos,this);
            RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(listAdapter);
        }

    }

}
