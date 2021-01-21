package com.ppal007.nxtlvl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ppal007.nxtlvl.R;
import com.ppal007.nxtlvl.activity.GalleryActivity;
import com.ppal007.nxtlvl.activity.GalleryExpandActivity;
import com.ppal007.nxtlvl.interFace.RvChildClick;
import com.ppal007.nxtlvl.model.ModelExpandParentRv;

import java.util.ArrayList;

public class AdapterRvParent extends RecyclerView.Adapter<AdapterRvParent.MyViewHolder> {

    private Context context;
    private String[] parentList;
    private ArrayList<ModelExpandParentRv> logo_list;
    private RvChildClick rvChildClick;

    public AdapterRvParent(Context context, String[] parentList, ArrayList<ModelExpandParentRv> logo_list, RvChildClick rvChildClick) {
        this.context = context;
        this.parentList = parentList;
        this.logo_list = logo_list;
        this.rvChildClick = rvChildClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rv_parent_sample, parent, false);

        return new AdapterRvParent.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //set res
        holder.textView_parent_title.setText(parentList[position]);
        //init rv child
        AdapterRvChild adapterRvChild = new AdapterRvChild(context,logo_list,position,rvChildClick);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context,
                RecyclerView.HORIZONTAL,
                false);
        holder.recyclerView_child.setLayoutManager(linearLayoutManager);
        holder.recyclerView_child.setItemAnimator(new DefaultItemAnimator());
        holder.recyclerView_child.setAdapter(adapterRvChild);

    }

    @Override
    public int getItemCount() {
        return parentList.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_parent_title;
        RecyclerView recyclerView_child;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_parent_title = itemView.findViewById(R.id.tv_title_rv_sample_expand);
            recyclerView_child = itemView.findViewById(R.id.rv_child);
        }
    }
}
