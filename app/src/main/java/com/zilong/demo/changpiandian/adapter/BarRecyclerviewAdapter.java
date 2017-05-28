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
 * Created by Administrator on 2017/5/26.
 */

public class BarRecyclerviewAdapter extends RecyclerView.Adapter<BarRecyclerviewAdapter.BarViewHolder> {
    private Context context;
    private ArrayList<Xiaoka> list;

    public BarRecyclerviewAdapter(Context context, ArrayList<Xiaoka> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public BarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bar, null);
        BarViewHolder holder = new BarViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BarViewHolder holder, int position) {
        Xiaoka xiaoka = list.get(position);
        holder.image_barphoto.setImageResource(xiaoka.getIcon());
        holder.tv_barname.setText(xiaoka.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class BarViewHolder extends RecyclerView.ViewHolder{
        protected final TextView tv_barname;
        private ImageView image_barphoto;
        public BarViewHolder(View itemView) {
            super(itemView);
            image_barphoto = (ImageView) itemView.findViewById(R.id.image_bar);
            tv_barname = (TextView) itemView.findViewById(R.id.tv_barname);
        }
    }
}
