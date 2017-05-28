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

/**
 * 下载中。这个只有在下载中才会展示下载中的歌曲。下载完成时界面为空。
 */
public class DownloadingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_downloading,null);
        return view;
    }
}
