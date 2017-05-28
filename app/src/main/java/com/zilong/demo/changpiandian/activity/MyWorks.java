package com.zilong.demo.changpiandian.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.adapter.WorksRecyclerviewAdapter;
import com.zilong.demo.changpiandian.model.WorksAlbum;

import java.util.ArrayList;

/**
 * 我的作品
 */
public class MyWorks extends AppCompatActivity{

    private LuRecyclerView works_lRecyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_works);
        initView();
        initData();
        initEvent();
    }

    private void initData() {
        ArrayList<WorksAlbum> albumArrayList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            WorksAlbum worksAlbum = new WorksAlbum();
            worksAlbum.setAlbum_image(R.mipmap.jay);
            worksAlbum.setAlbum_name("哎哟不错哦");
            worksAlbum.setAlbum_saleCount(328837);
            worksAlbum.setAlbum_collectionsCount(2229911);
            albumArrayList.add(worksAlbum);
        }

        LinearLayoutManager manager = new LinearLayoutManager(this);
        works_lRecyclerview.setLayoutManager(manager);
        WorksRecyclerviewAdapter worksRecyclerviewAdapter = new WorksRecyclerviewAdapter(this, albumArrayList);
        LuRecyclerViewAdapter adapter = new LuRecyclerViewAdapter(worksRecyclerviewAdapter);
        works_lRecyclerview.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MyWorks.this, ItemWorkActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initEvent() {

    }

    private void initView() {
        works_lRecyclerview = (LuRecyclerView) findViewById(R.id.works_recyclerview);
    }

}
