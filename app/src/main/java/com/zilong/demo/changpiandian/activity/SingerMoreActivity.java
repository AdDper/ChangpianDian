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
 * 歌手更多
 */
public class SingerMoreActivity extends AppCompatActivity {

    protected LuRecyclerView recyclerview_singer_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singer_more);
        initView();
        initData();
    }

    private void initView() {
        recyclerview_singer_more = (LuRecyclerView) findViewById(R.id.singer_more_Lurecyclerview);
    }

    private void initData() {
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerview_singer_more.setLayoutManager(manager);
        ArrayList<Xiaoka> data = initRecyclerviewData();
        /**
         * list泛型改一下
         */
/*        MusicManLuRecyclerviewAdapter recyclerviewAdapter = new MusicManLuRecyclerviewAdapter(this, data);
        LuRecyclerViewAdapter adapter = new LuRecyclerViewAdapter(recyclerviewAdapter);
        recyclerview_singer_more.setAdapter(adapter);*/
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
