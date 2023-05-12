package com.example.moviiapk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<clasecontructor> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<clasecontructor> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.etiqueta, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<clasecontructor> items) {
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView numerotransferencia, cantidadtransferencia, fechatransferencia, horatransfernecia;

        public ViewHolder(View itemView) {
            super(itemView);
            numerotransferencia = itemView.findViewById(R.id.nameTextView);
            cantidadtransferencia = itemView.findViewById(R.id.statusTextView);
            fechatransferencia = itemView.findViewById(R.id.cityTextView);
            horatransfernecia = itemView.findViewById(R.id.precioTextView);
        }

        void bindData(final clasecontructor item) {
            numerotransferencia.setText(item.getNumerotransferenia());
            cantidadtransferencia.setText(item.getCantidadtransferencia());
            fechatransferencia.setText(item.getFechatransferencia());
            horatransfernecia.setText(item.getHoratransfernecia());
        }
    }
}
