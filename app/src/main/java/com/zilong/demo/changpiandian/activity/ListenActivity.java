package com.zilong.demo.changpiandian.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.zilong.demo.changpiandian.R;

/**
 * 我的音乐——试听记录
 */
public class ListenActivity extends AppCompatActivity {

    protected ListView lv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);
        initView();

        initData();

        initEvent();
    }

    private void initEvent() {

    }

    private void initData() {
        //这里从保存的试听记录中取出数据，并添加进集合中。

    }

    private void initView() {
        lv_show = (ListView) findViewById(R.id.listview);

    }
}
