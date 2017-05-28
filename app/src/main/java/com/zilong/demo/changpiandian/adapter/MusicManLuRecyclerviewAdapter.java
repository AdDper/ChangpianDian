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

import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.model.MusicLibrary;
import com.zilong.demo.changpiandian.model.Xiaoka;
import com.zilong.demo.changpiandian.util.ImageUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class MusicManLuRecyclerviewAdapter extends RecyclerView.Adapter<MusicManLuRecyclerviewAdapter.SingerViewHolder> {

    private Context context;
//    private ArrayList<Xiaoka> xiaokaList;
    private ArrayList<MusicLibrary.DataBean.SingerlistBean> singerlist;

    public MusicManLuRecyclerviewAdapter(Context context, ArrayList<MusicLibrary.DataBean.SingerlistBean> singerlist) {
        this.context = context;
        this.singerlist = singerlist;
    }

/*    public MusicManLuRecyclerviewAdapter(Context context, ArrayList<Xiaoka> xiaokaList) {
        this.context = context;
        this.xiaokaList = xiaokaList;
    }*/

    @Override
    public SingerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_xiaoka, null);
        SingerViewHolder singerViewHolder = new SingerViewHolder(view);
        return singerViewHolder;
    }

    @Override
    public void onBindViewHolder(SingerViewHolder holder, int position) {
//        Xiaoka xiaoka = singerlist.get(position);
        MusicLibrary.DataBean.SingerlistBean singerlistBean = singerlist.get(position);
//        holder.image_xiaoka_icon.setImageResource(xiaoka.getIcon());
        holder.tv_xiaoka_name.setText(singerlistBean.getUserName());
        Resources resources = context.getResources();
        Bitmap bitmap = BitmapFactory.decodeResource(resources, Integer.parseInt(singerlistBean.getHeadPortrait()));
        Bitmap roundBitmap = ImageUtil.toRoundBitmap(bitmap);
        holder.image_xiaoka_icon.setImageBitmap(roundBitmap);
    }

    @Override
    public int getItemCount() {
        return singerlist.size();
    }

    class SingerViewHolder extends RecyclerView.ViewHolder{
        protected final ImageView image_xiaoka_icon;
        protected final TextView tv_xiaoka_name;
        private ImageView image_icon;
        private TextView tv_name;

        public SingerViewHolder(View itemView) {
            super(itemView);
            image_xiaoka_icon = (ImageView) itemView.findViewById(R.id.image_icon);
            tv_xiaoka_name = (TextView) itemView.findViewById(R.id.tv_name);


        }
    }
}
