package com.zilong.demo.changpiandian.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.fragment.DownloadedFragment;
import com.zilong.demo.changpiandian.fragment.DownloadingFragment;

/**
 * 我的音乐-下载
 */
public class DownloadActivity extends AppCompatActivity implements View.OnClickListener {

    protected TextView tv_ok;
    protected TextView tv_loading;
    private android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initView();

        initData();

        initEvent();
    }

    private void initData() {
        fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.download_fg,new DownloadedFragment());
        transaction.commit();
    }

    private void initEvent() {
        tv_ok.setOnClickListener(this);
        tv_loading.setOnClickListener(this);
    }

    private void initView() {
        tv_ok = (TextView) findViewById(R.id.download_ok);
        tv_loading = (TextView) findViewById(R.id.download_doing);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download_ok:
                tv_ok.setTextColor(Color.GREEN);
                tv_loading.setTextColor(Color.GRAY);
                android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.download_fg,new DownloadedFragment());
                transaction.commit();
                break;
            case R.id.download_doing:
                tv_loading.setTextColor(Color.GREEN);
                tv_ok.setTextColor(Color.GREEN);
                android.support.v4.app.FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.replace(R.id.download_fg,new DownloadingFragment());
                beginTransaction.commit();
                break;
        }
    }
}
