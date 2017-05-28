package com.zilong.demo.changpiandian.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.leakcanary.RefWatcher;
import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.application.MyApplication;
import com.zilong.demo.changpiandian.model.AlbumInfo;
import com.zilong.demo.changpiandian.model.MusicLibrary;
import com.zilong.demo.changpiandian.util.ImageUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class MlRecyclerviewAdapter extends RecyclerView.Adapter<MlRecyclerviewAdapter.MyViewHolder> {
    private Context context;
    private List<MusicLibrary.DataBean.AlbumlistBean> albumInfoList;

/*    public MlRecyclerviewAdapter(Context context, List<AlbumInfo> albumInfoList) {
        this.context = context;
        this.albumInfoList = albumInfoList;
    }*/

    public MlRecyclerviewAdapter(Context context, List<MusicLibrary.DataBean.AlbumlistBean> albumInfoList) {
        this.context = context;
        this.albumInfoList = albumInfoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RefWatcher refWatcher = MyApplication.getRefWatcher();
        refWatcher.watch(context);
        View view = LayoutInflater.from(context).inflate(R.layout.item_hotalbum, null);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        MusicLibrary.DataBean.AlbumlistBean albumlistBean = albumInfoList.get(position);
        holder.singer.setText( albumlistBean.getUserName());
        holder.albumName.setText(albumlistBean.getAlbumName());
        holder.price.setImageResource(R.mipmap.price);
        Glide.with(context).load(albumlistBean.getGetAlbumPhotoUrl()).placeholder(R.mipmap.jay).into(holder.image_album);
//        holder.image_album.setImageResource(albumlistBean.getGetAlbumPhotoUrl());
    }

    @Override
    public int getItemCount() {
        return albumInfoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView image_album;
        private TextView singer;
        private TextView albumName;
        private ImageView price;

        public MyViewHolder(View itemView) {
            super(itemView);
            image_album = (ImageView) itemView.findViewById(R.id.image_album);
            singer = (TextView) itemView.findViewById(R.id.singer);
            albumName = (TextView) itemView.findViewById(R.id.albumName);
            price = (ImageView) itemView.findViewById(R.id.price_album);
        }
    }
}
