package com.example.moviiapk;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<clasecontructor> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(Context context) {
        this.context = context;
        this.mData = new ArrayList<>();
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View  view = mInflater.inflate(R.layout.etiqueta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        clasecontructor item = mData.get(position);
            holder.bindData(mData.get(position));

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void setItems(List<clasecontructor> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public void addItem(clasecontructor usuario) {
        mData.add(usuario);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView numerotransferencia, cantidadtransferencia, fechatransferencia, horatransfernecia;
        LinearLayout layout;
        ConstraintLayout constraintLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            numerotransferencia = itemView.findViewById(R.id.nameTextView);
            cantidadtransferencia = itemView.findViewById(R.id.statusTextView);
            fechatransferencia = itemView.findViewById(R.id.cityTextView);
            horatransfernecia = itemView.findViewById(R.id.precioTextView);
            layout = itemView.findViewById(R.id.layout_container);
            constraintLayout = itemView.findViewById(R.id.cv2);

        }

        void bindData(clasecontructor item) {
            numerotransferencia.setText(item.getNumerotransferenia());
            cantidadtransferencia.setText(item.getCantidadtransferencia());
            fechatransferencia.setText(item.getFechatransferencia());
            horatransfernecia.setText(item.getHoratransfernecia());
            Drawable drawable;
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            if (!item.isRecivida(true)){
                drawable =layout.getContext().getResources().getDrawable(R.drawable.borderojo);
                constraintSet.connect(R.id.layout_container,ConstraintSet.END,R.id.cv2,ConstraintSet.END,0);
            }else {
                constraintSet.connect(R.id.layout_container,ConstraintSet.START,R.id.cv2,ConstraintSet.START,0);
                drawable = layout.getContext().getResources().getDrawable(R.drawable.bordeverde);
            }
            constraintSet.applyTo(constraintLayout);
            layout.setBackground(drawable);


        }


    }


}

