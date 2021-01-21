package com.ppal007.nxtlvl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.ppal007.nxtlvl.R;
import com.ppal007.nxtlvl.adapter.viewPager.ViewPagerAdapter;

public class PreViewActivity extends AppCompatActivity {

    private int pos;
    private int parent_pos;
    private String parent_title;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView textViewTitle;
    private ImageView imageView_back;
    private TextView textView_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //action bar transparent
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setContentView(R.layout.activity_pre_view);

        //get bundle value from gl expand
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            pos = bundle.getInt("ex_path");
            parent_title = bundle.getString("ex_parent_title");
            parent_pos = bundle.getInt("ex_parent_pos");
        }


        //init resources
        int[] res_1 = new int[]{
                R.drawable.g_1,
                R.drawable.g_2,
                R.drawable.g_3,
                R.drawable.g_4
        };
        int[] res_2 = new int[]{
                R.drawable.g_5,
                R.drawable.g_6,
                R.drawable.g_7,
                R.drawable.g_8
        };
        int[] res_3 = new int[]{
                R.drawable.g_1,
                R.drawable.g_2,
                R.drawable.g_3,
                R.drawable.g_4
        };

        //find xml
        viewPager = findViewById(R.id.view_pager_preview);
        tabLayout = findViewById(R.id.tabLayout_Id);
        imageView_back = findViewById(R.id.img_back_preview);
        textView_back = findViewById(R.id.tv_preview_back);
        textViewTitle = findViewById(R.id.tv_preview_title);
        textViewTitle.setText(parent_title);


        if (parent_pos==0){
            initViewPager(res_1);
        }else if (parent_pos==1){
            initViewPager(res_2);
        }else if (parent_pos == 2){
            initViewPager(res_3);
        }

        //image view back click listener
        imageView_back.setOnClickListener(v -> finish());
        //text view back click listener
        textView_back.setOnClickListener(v -> finish());


    }

    private void initViewPager(int[] res) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(PreViewActivity.this,res);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}