package com.zilong.demo.changpiandian.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zilong.demo.changpiandian.R;

/**
 * Created by Administrator on 2017/5/22.
 */

public class DownloadedFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_downloaded,null);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }
}
