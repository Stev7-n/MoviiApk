package com.example.moviiapk;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BaseDeDatos extends SQLiteOpenHelper {
    public BaseDeDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE usuario (nombreUsuario TEXT, correoUsuario TEXT," +
                "numeroUsuario TEXT  primary key,  CantDinero TEXT, contraseñaUsuario TEXT, confirmarUsuario TEXT)");

        db.execSQL("CREATE TABLE transferencias (numeroTransferencia TEXT, confirmarTransferencia TEXT, cantidadTransferencia TEXT, fecha TEXT, hora TEXT)");
    }

    public Cursor getTransferencias() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"numeroTransferencia", "confirmarTransferencia", "cantidadTransferencia", "fecha", "hora"};
        Cursor cursor = db.query("transferencias", columns, null, null, null, null, null);
        return cursor;
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
