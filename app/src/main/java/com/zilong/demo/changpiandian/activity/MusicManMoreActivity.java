package com.zilong.demo.changpiandian.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.adapter.MusicManLuRecyclerviewAdapter;
import com.zilong.demo.changpiandian.model.Xiaoka;

import java.util.ArrayList;

/**
 * 音乐人更多
 */
public class MusicManMoreActivity extends AppCompatActivity {

    protected LuRecyclerView recyclerview_more_musicman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_man_more);

        initView();
        initData();
    }

    private void initView() {
        recyclerview_more_musicman = (LuRecyclerView) findViewById(R.id.musicman_more_recyclerview);
    }

    private void initData() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerview_more_musicman.setLayoutManager(manager);
        ArrayList<Xiaoka> data = initRecyclerviewData();


/*        MusicManLuRecyclerviewAdapter recyclerviewAdapter = new MusicManLuRecyclerviewAdapter(this, data);
        LuRecyclerViewAdapter adapter = new LuRecyclerViewAdapter(recyclerviewAdapter);
        recyclerview_more_musicman.setAdapter(adapter);*/
    }

    /**
     * 加载recyclerview的数据
     * @return
     */
    private ArrayList<Xiaoka> initRecyclerviewData() {
        ArrayList<Xiaoka> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Xiaoka xiaoka = new Xiaoka();
            xiaoka.setName("YOYO");
            xiaoka.setIcon(R.mipmap.kelala);
            list.add(xiaoka);
        }
        return list;
    }

}
