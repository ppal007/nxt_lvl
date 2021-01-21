package com.ppal007.nxtlvl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.ppal007.nxtlvl.R;
import com.ppal007.nxtlvl.adapter.AdapterRvParent;
import com.ppal007.nxtlvl.interFace.RvChildClick;
import com.ppal007.nxtlvl.model.ModelExpandParentRv;
import com.ppal007.nxtlvl.model.ModelGallery;

import java.util.ArrayList;
import java.util.List;

public class GalleryExpandActivity extends AppCompatActivity implements RvChildClick {

    private RecyclerView recyclerView_gl_expand;
    private ArrayList<ModelExpandParentRv> demo;
    private String[] parent_list;
    private int[] res_1;
    private int[] res_2;
    private int[] res_3;
    private ArrayList<ModelExpandParentRv> logo_list;
    private ImageView imageView_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //action bar transparent
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setContentView(R.layout.activity_gallery_expand);

        //find xml
        recyclerView_gl_expand = findViewById(R.id.rv_gl_expand);
        imageView_back = findViewById(R.id.img_view_back_gl_expand);

        //image view back click listener
        imageView_back.setOnClickListener(v -> finish());

        //init resource
        parent_list = getResources().getStringArray(R.array.gl_expand_list);
        res_1 = new int[]{
                R.drawable.g_1,
                R.drawable.g_2,
                R.drawable.g_3,
                R.drawable.g_4
        };
        res_2 = new int[]{
                R.drawable.g_5,
                R.drawable.g_6,
                R.drawable.g_7,
                R.drawable.g_8
        };
        res_3 = new int[]{
                R.drawable.g_1,
                R.drawable.g_2,
                R.drawable.g_3,
                R.drawable.g_4
        };

        //init model
        logo_list = new ArrayList<>();
        for (int i = 0; i < res_1.length; i++) {
            ModelExpandParentRv model = new ModelExpandParentRv(res_1[i],res_2[i],res_3[i]);
            logo_list.add(model);
        }

        //init rv
        initRv(logo_list);
    }

    private void initRv(ArrayList<ModelExpandParentRv> logo_list) {
        AdapterRvParent adapterRvParent = new AdapterRvParent(getApplicationContext(),parent_list,logo_list,this);
        recyclerView_gl_expand.setLayoutManager(new LinearLayoutManager(GalleryExpandActivity.this));
        recyclerView_gl_expand.setHasFixedSize(true);
        recyclerView_gl_expand.setAdapter(adapterRvParent);
    }


    @Override
    public void onClick(int parentPos, int childPos) {
//        Toast.makeText(this, "par : "+parentPos+"\n"+"child : "+childPos, Toast.LENGTH_LONG).show();
        int path = 0;

        if (parentPos==0){
            path = logo_list.get(childPos).getChild_logo_1();

        }else if (parentPos==1){
            path = logo_list.get(childPos).getChild_logo_2();

        }else if (parentPos==2){
            path = logo_list.get(childPos).getChild_logo_3();

        }

        Intent intent = new Intent(getApplicationContext(),PreViewActivity.class);
        intent.putExtra("ex_path",path);
        intent.putExtra("ex_parent_title",parent_list[parentPos]);
        intent.putExtra("ex_parent_pos",parentPos);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}