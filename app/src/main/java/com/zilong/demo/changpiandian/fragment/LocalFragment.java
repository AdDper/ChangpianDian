package com.zilong.demo.changpiandian.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.activity.BuyRecordsActivity;
import com.zilong.demo.changpiandian.activity.CollectionsActivity;
import com.zilong.demo.changpiandian.activity.DownloadActivity;
import com.zilong.demo.changpiandian.activity.ListenActivity;
import com.zilong.demo.changpiandian.activity.LocalMusicActivity;


/**
 * 我的音乐
 */

public class LocalFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RelativeLayout rel_local,rel_listen,rel_download,rel_buy,rel_collections;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.layout_local,container,false);
/*        ViewHolder holder = new ViewHolder(view);
        if(container!=null){

        }else{

        }*/
        initView(view);
        initEvent();
        return view;
    }

    /**
     * 点击item跳转到相应的界面
     */
    private void initEvent() {
        rel_local.setOnClickListener(this);
        rel_listen.setOnClickListener(this);
        rel_download.setOnClickListener(this);
        rel_buy.setOnClickListener(this);
        rel_collections.setOnClickListener(this);
    }
    /**
         * 这里将适配器与控件绑定。
         * 先要获取数据源。也就是本地音乐的集合。
         * 单个音乐的集合里有它的文件目录地址（用来播放用），音乐名，歌手名，专辑名。
          */

    private void initView(View view) {
        rel_local = (RelativeLayout) view.findViewById(R.id.rel_local);
        rel_listen = (RelativeLayout) view.findViewById(R.id.rel_listen);
        rel_download = (RelativeLayout) view.findViewById(R.id.rel_download);
        rel_buy = (RelativeLayout) view.findViewById(R.id.rel__buy);
        rel_collections = (RelativeLayout) view.findViewById(R.id.rel_collections);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     *         这几个的界面实际上都是重复的，无非就是一个列表视图recyclerview。
     *          那么可不可以将其应用为fragment.
     *          这样的话也减少内存的占用。
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rel_local:
                Intent intent = new Intent(getContext(), LocalMusicActivity.class);
                startActivity(intent);
                break;
            case R.id.rel_listen:
                Intent intent1 = new Intent(getContext(), ListenActivity.class);
                startActivity(intent1);
                break;
            case R.id.rel_download:
                Intent intent2 = new Intent(getContext(), DownloadActivity.class);
                startActivity(intent2);
                break;
            case R.id.rel__buy:
                Intent intent3 = new Intent(getContext(), BuyRecordsActivity.class);
                startActivity(intent3);
                break;
            case R.id.rel_collections:
                Intent intent4 = new Intent(getContext(), CollectionsActivity.class);
                startActivity(intent4);
                break;
        }
    }
/*
    class ViewHolder{
        private TextView tv_songName;
        private TextView tv_singerName;

        public ViewHolder(View view) {
            tv_songName = (TextView) view.findViewById(R.id.tv_songName);
            tv_singerName = (TextView) view.findViewById(R.id.tv_singerName);
        }
    }*/

}
