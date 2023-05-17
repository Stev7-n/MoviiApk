package com.example.moviiapk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Object> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(Context context) {
        this.context = context;
        this.mData = new ArrayList<>();
        this.mInflater = LayoutInflater.from(context);
    }

    public ListAdapter(ArrayList<Object> objects, historial historial) {
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = mInflater.inflate(R.layout.etiqueta, parent, false);
        } else {
            view = mInflater.inflate(R.layout.etiqueta2, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Object item = mData.get(position);
        if (item instanceof clasecontructor) {
            holder.bindData((clasecontructor) item);
        } else if (item instanceof claseConstructor2) {
            holder.bindData((claseConstructor2) item);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mData.get(position);
        if (item instanceof clasecontructor) {
            return 1;
        } else if (item instanceof claseConstructor2) {
            return 2;
        }
        return super.getItemViewType(position);
    }

    public void setItems(List<Object> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(clasecontructor usuario) {
        mData.add(usuario);
        notifyDataSetChanged();
    }

    public void addItems(List<claseConstructor2> transferenciasRecibidas) {
        mData.addAll(transferenciasRecibidas);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView numerotransferencia, cantidadtransferencia, fechatransferencia, horatransfernecia;
        TextView numeroQEnvio, cantidadQRecibio, fechaQTrans, horaQTrans;

        public ViewHolder(View itemView) {
            super(itemView);
            numerotransferencia = itemView.findViewById(R.id.nameTextView);
            cantidadtransferencia = itemView.findViewById(R.id.statusTextView);
            fechatransferencia = itemView.findViewById(R.id.cityTextView);
            horatransfernecia = itemView.findViewById(R.id.precioTextView);
            numeroQEnvio = itemView.findViewById(R.id.nameTextView2);
            cantidadQRecibio = itemView.findViewById(R.id.statusTextView2);
            fechaQTrans = itemView.findViewById(R.id.cityTextView2);
            horaQTrans = itemView.findViewById(R.id.precioTextView2);
        }

        void bindData(final clasecontructor item) {
            numerotransferencia.setText(item.getNumerotransferenia());
            cantidadtransferencia.setText(item.getCantidadtransferencia());
            fechatransferencia.setText(item.getFechatransferencia());
            horatransfernecia.setText(item.getHoratransfernecia());

        }

        void bindData(final claseConstructor2 item) {
            numeroQEnvio.setText(item.getnumeroQEnvio());
            cantidadQRecibio.setText(item.getcantidadQRecibio());
            fechaQTrans.setText(item.getfechaQTrans());
            horaQTrans.setText(item.gethoraQTrans());

        }
    }
}
