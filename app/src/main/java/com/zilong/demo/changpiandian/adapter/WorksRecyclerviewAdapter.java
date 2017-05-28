package com.zilong.demo.changpiandian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.model.WorksAlbum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/22.
 */

public class WorksRecyclerviewAdapter extends RecyclerView.Adapter<WorksRecyclerviewAdapter.WorksViewHolder> {
    private Context context;
    private ArrayList<WorksAlbum> list;

    public WorksRecyclerviewAdapter(Context context, ArrayList<WorksAlbum> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public WorksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_works, null);
        WorksViewHolder holder = new WorksViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(WorksViewHolder holder, int position) {
        WorksAlbum album = list.get(position);

        holder.works_albumImage.setImageResource(album.getAlbum_image());
        holder.tv_works_albumName.setText(album.getAlbum_name());
        holder.tv_works_saleCount.setText(String.valueOf(album.getAlbum_saleCount()));
        holder.tv_works_collectionsCount.setText(String.valueOf(album.getAlbum_collectionsCount()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  WorksViewHolder extends RecyclerView.ViewHolder{

        protected final ImageView works_albumImage;
        protected final TextView tv_works_albumName;
        protected final TextView tv_works_saleCount;
        protected final TextView tv_works_collectionsCount;

        public WorksViewHolder(View itemView) {
            super(itemView);
            works_albumImage = (ImageView) itemView.findViewById(R.id.works_image);
            tv_works_albumName = (TextView) itemView.findViewById(R.id.myworks_albumname);
            tv_works_saleCount = (TextView) itemView.findViewById(R.id.myworks_saleCount);
            tv_works_collectionsCount = (TextView) itemView.findViewById(R.id.myworks_collectionsCount);

        }
    }
}
