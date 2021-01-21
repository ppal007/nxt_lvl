package com.ppal007.nxtlvl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppal007.nxtlvl.R;
import com.ppal007.nxtlvl.adapter.AdapterRvGallery;
import com.ppal007.nxtlvl.model.ModelGallery;

import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private RecyclerView recyclerView_gallery;
    private int[] gallery_res;
    private TextView textView_view_all;
    private ImageView imageView_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //action bar transparent
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setContentView(R.layout.activity_gallery);

        //find xml
        recyclerView_gallery = findViewById(R.id.rv_gallery);
        textView_view_all = findViewById(R.id.tv_view_all);
        imageView_back = findViewById(R.id.img_back_gl);

        //init gallery res
        gallery_res = new int[]{
                R.drawable.g_1,
                R.drawable.g_2,
                R.drawable.g_3,
                R.drawable.g_4
        };
        //init model
        ArrayList<ModelGallery> logo_list = new ArrayList<>();
        for (int i = 0; i < gallery_res.length; i++) {
            ModelGallery model = new ModelGallery(gallery_res[i]);
            logo_list.add(model);
        }

        //init recycler view
        initRv(logo_list);

        //text view view all click listener
        textView_view_all.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(),GalleryExpandActivity.class);
            startActivity(intent);
        });

        //image view back click listener
        imageView_back.setOnClickListener(v -> finish());
    }

    private void initRv(ArrayList<ModelGallery> logo_list) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(GalleryActivity.this,
                RecyclerView.HORIZONTAL,
                false);
        recyclerView_gallery.setLayoutManager(linearLayoutManager);
        recyclerView_gallery.setItemAnimator(new DefaultItemAnimator());
        AdapterRvGallery adapter = new AdapterRvGallery(getApplicationContext(),logo_list);
        recyclerView_gallery.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}