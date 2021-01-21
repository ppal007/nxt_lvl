package com.ppal007.nxtlvl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ppal007.nxtlvl.R;
import com.ppal007.nxtlvl.interFace.RvChildClick;
import com.ppal007.nxtlvl.model.ModelExpandParentRv;

import java.util.ArrayList;

public class AdapterRvChild extends RecyclerView.Adapter<AdapterRvChild.MyViewHolder> {

    private Context context;
    private ArrayList<ModelExpandParentRv> logo_list;
    private RvChildClick rvChildClick;
    private int pos;

    public AdapterRvChild(Context context, ArrayList<ModelExpandParentRv> logo_list,int pos,RvChildClick rvChildClick) {
        this.context = context;
        this.logo_list = logo_list;
        this.pos = pos;
        this.rvChildClick = rvChildClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_gallery_sample, parent, false);

        return new AdapterRvChild.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //set views
        if (pos==0){
            Glide.with(context).load(logo_list.get(position).getChild_logo_1()).into(holder.imageView);
        }else if (pos==1){
            Glide.with(context).load(logo_list.get(position).getChild_logo_2()).into(holder.imageView);
        }else if (pos==2){
            Glide.with(context).load(logo_list.get(position).getChild_logo_3()).into(holder.imageView);
        }

        //on click
        holder.itemView.setOnClickListener(v -> rvChildClick.onClick(pos,position));

    }

    @Override
    public int getItemCount() {
        return logo_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_rv_gl_sample);
        }
    }
}
