package com.zilong.demo.changpiandian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.model.Xiaoka;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/19.
 */

public class SingerLRecyclerviewAdapter extends RecyclerView.Adapter<SingerLRecyclerviewAdapter.SingerViewHolder> {

    private Context context;
    private ArrayList<Xiaoka> xiaokaList;

    public SingerLRecyclerviewAdapter(Context context, ArrayList<Xiaoka> xiaokaList) {
        this.context = context;
        this.xiaokaList = xiaokaList;
    }

    @Override
    public SingerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_xiaoka, null);
        SingerViewHolder singerViewHolder = new SingerViewHolder(view);
        return singerViewHolder;
    }

    @Override
    public void onBindViewHolder(SingerViewHolder holder, int position) {
        Xiaoka xiaoka = xiaokaList.get(position);
        holder.image_xiaoka_icon.setImageResource(xiaoka.getIcon());
        holder.tv_xiaoka_name.setText(xiaoka.getName());
    }

    @Override
    public int getItemCount() {
        return xiaokaList.size();
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
