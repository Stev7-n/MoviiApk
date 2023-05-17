package com.example.moviiapk;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class historial extends AppCompatActivity {

    String numeroUsuario;
    ListAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial);
        Bundle bundle = getIntent().getExtras();
        numeroUsuario = bundle.getString("numeroUsuario");
        init();
    }

    public void init() {
        adapter = new ListAdapter(this);
        recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        consulta();
    }

    private void consulta() {
        BaseDeDatos admin = new BaseDeDatos(this, "admin", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        try (Cursor cursor = db.rawQuery("SELECT numeroUsuario, numeroTransferencia, cantidadTransferencia, fechaTransferencia, horaTransferencia FROM transferencias WHERE numeroUsuario = '" + numeroUsuario + "' OR numeroTransferencia = '" + numeroUsuario + "'", null)) {
            if (!cursor.moveToFirst()) {
                Toast.makeText(this, "No se encontraron transacciones para este usuario", Toast.LENGTH_SHORT).show();
                return;
            }

            List<Object> transferenciasRealizadasYRecibidas = new ArrayList<>();

            do {
                if (cursor.getString(0).equals(numeroUsuario)) {
                    clasecontructor usuario = new clasecontructor();
                    usuario.setNumerotransferenia(cursor.getString(1));
                    usuario.setCantidadtransferencia(cursor.getString(2));
                    usuario.setFechatransferencia(cursor.getString(3));
                    usuario.setHoratransfernecia(cursor.getString(4));
                    transferenciasRealizadasYRecibidas.add(usuario);
                } else {
                    claseConstructor2 transferenciaRecibida = new claseConstructor2();
                    transferenciaRecibida.setnumeroQEnvio(cursor.getString(0));
                    transferenciaRecibida.setcantidadQRecibio(cursor.getString(2));
                    transferenciaRecibida.setfechaQTrans(cursor.getString(3));
                    transferenciaRecibida.sethoraQTrans(cursor.getString(4));
                    transferenciasRealizadasYRecibidas.add(transferenciaRecibida);
                }
            } while (cursor.moveToNext());

            adapter.setItems(transferenciasRealizadasYRecibidas);
        }
        db.close();
    }
}
