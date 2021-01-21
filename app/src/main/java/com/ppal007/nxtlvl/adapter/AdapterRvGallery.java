package com.ppal007.nxtlvl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ppal007.nxtlvl.R;
import com.ppal007.nxtlvl.model.ModelGallery;

import java.util.ArrayList;

public class AdapterRvGallery extends RecyclerView.Adapter<AdapterRvGallery.MyViewHolder> {

    private Context context;
    private ArrayList<ModelGallery> logo;

    public AdapterRvGallery(Context context, ArrayList<ModelGallery> logo) {
        this.context = context;
        this.logo = logo;
    }

    @NonNull
    @Override
    public AdapterRvGallery.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_gallery_sample, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRvGallery.MyViewHolder holder, int position) {
        //init resource
        Glide.with(context).load(logo.get(position).getLogo()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return logo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_rv_gl_sample);
        }
    }
}
