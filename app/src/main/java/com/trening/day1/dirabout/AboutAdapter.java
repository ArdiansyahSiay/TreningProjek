package com.trening.day1.dirabout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.trening.day1.AboutActivity;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.squareup.picasso.Picasso;
import com.trening.day1.R;
import java.util.ArrayList;


public class AboutAdapter extends RecyclerView.Adapter<AboutAdapter.ListViewHolder> {

    public ArrayList<DataTim> listTim;
    public AboutAdapter (ArrayList<DataTim> list){
        this.listTim = list;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recy_about,viewGroup,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {

        DataTim dataTim = listTim.get(position);
        Glide.with(holder.itemView.getContext()).load(dataTim.getImgTim()).apply(new RequestOptions())
                .override(40,40).into(holder.imgTim);
        holder.namatim.setText(dataTim.getNama());
        holder.univTim.setText(dataTim.getUniv());
        holder.alamat.setText(dataTim.getAlmt());
        holder.tlpTim.setText(dataTim.getTlp());
    }

    @Override
    public int getItemCount() {
        return listTim.size();
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTim;
        TextView namatim,univTim,alamat,tlpTim;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgTim = itemView.findViewById(R.id.imgTim);
            namatim = itemView.findViewById(R.id.namaTim);
            univTim = itemView.findViewById(R.id.univTim);
            alamat = itemView.findViewById(R.id.almtTimt);
            tlpTim = itemView.findViewById(R.id.tlpTim);
        }
    }
}