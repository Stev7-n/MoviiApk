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

    private TextView tv,tv1;
    List<clasecontructor> elementos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etiqueta);

        init();
    }
//            ESTOOOOOOOOOOOOOO
    public void init(){
        elementos = new ArrayList<>();
        BaseDeDatos admin = new BaseDeDatos(this,"admin",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String saldo = bundle.getString("saldo");
        tv1.setText(saldo);
        tv.setText(name);
        Cursor fila = db.rawQuery("select nameuser,namegame,saldo,precio from history where nameuser='"+name+"'", null);
        for (int i = 0;i<fila.getCount();){
            if(fila.moveToNext()){
                elementos.add(new clasecontructor(fila.getString(0),fila.getString(1),fila.getString(2),fila.getString(3)));
            }
            i++;
        }
        ListAdapter listAdapter = new ListAdapter(elementos,this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }


    public void cerrar(View v){
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues registro = new ContentValues();
        String name = tv.getText().toString();
        registro.put("activo",false);
        int x= db.update("user",registro,"name='"+name+"'",null);
        Intent i = new Intent(this,MainActivity.class);
        db.close();
        startActivity(i);
        finish();
    }
//    ESTOOOOOOOO
}
