package com.example.moviiapk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter2 extends RecyclerView.Adapter<ListAdapter2.ViewHolder> {
    private List<claseConstructor2> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter2(List<claseConstructor2> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.etiqueta2, null);
        return new ListAdapter2.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<claseConstructor2> items) {
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView numeroQEnvio, cantidadQRecibio, fechaQTrans, horaQTrans;

        public ViewHolder(View itemView) {
            super(itemView);
            numeroQEnvio = itemView.findViewById(R.id.nameTextView2);
            cantidadQRecibio = itemView.findViewById(R.id.statusTextView2);
            fechaQTrans = itemView.findViewById(R.id.cityTextView2);
            horaQTrans = itemView.findViewById(R.id.precioTextView2);
        }

        void bindData(final claseConstructor2 item) {
            numeroQEnvio.setText(item.getnumeroQEnvio());
            cantidadQRecibio.setText(item.getcantidadQRecibio());
            fechaQTrans.setText(item.getfechaQTrans());
            horaQTrans.setText(item.gethoraQTrans());
        }
    }
}