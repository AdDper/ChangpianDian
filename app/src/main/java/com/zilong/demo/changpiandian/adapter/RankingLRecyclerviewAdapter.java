package com.zilong.demo.changpiandian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.model.RankingInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19.
 */

public class RankingLRecyclerviewAdapter extends RecyclerView.Adapter<RankingLRecyclerviewAdapter.RankingViewHolder> implements View.OnClickListener {
    private Context context;
    private List<RankingInfo> rankingInfoList;

    public RankingLRecyclerviewAdapter(Context context, List<RankingInfo> rankingInfoList) {
        this.context = context;
        this.rankingInfoList = rankingInfoList;
    }

    @Override
    public RankingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_ranking, null);
            RankingViewHolder holder = new RankingViewHolder(view);
            return holder;
    }

    @Override
    public void onBindViewHolder(RankingViewHolder holder, int position) {
        Log.i("TAG", "onBindViewHolder: "+position);
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
        RankingInfo rankingInfo = rankingInfoList.get(position);
        displayContents(holder,rankingInfo);
    }

    /**
     * 现在遇到的问题是屏幕上滑后再滑会顶部。前三个的标示就消失不见了。
     * 因为复用的原因，position是在变化的，所以
     * @param holder
     * @param rankingInfo
     */
    protected void displayContents(RankingViewHolder holder, RankingInfo rankingInfo) {
        int ranking = rankingInfo.getImage_ranking();
        if (ranking == 1){
            Glide.with(context).load(R.mipmap.first).placeholder(R.mipmap.loading).into(holder.image_ranking);
//            holder.image_ranking.setImageResource(R.mipmap.first);
        }else if (ranking == 2){
            Glide.with(context).load(R.mipmap.second).placeholder(R.mipmap.loading).into(holder.image_ranking);

//            holder.image_ranking.setImageResource(R.mipmap.second);
        }else if (ranking == 3){
            Glide.with(context).load(R.mipmap.third).placeholder(R.mipmap.loading).into(holder.image_ranking);
//            holder.image_ranking.setImageResource(R.mipmap.third);

        }else {
            holder.image_ranking.setVisibility(View.INVISIBLE);
//        holder.image_singer.setImageResource(rankingInfo.getImage_singer());
        }
        Glide.with(context).load("http://i.shangc.net/allimg/140807/17-140PGA045.jpg").placeholder(R.mipmap.loading).into(holder.image_singer);
        holder.tv_song.setText(rankingInfo.getTv_song());
        holder.tv_heat.setText(rankingInfo.getTv_heat());
        holder.tv_info.setText(rankingInfo.getTv_info());
    }

    @Override
    public int getItemCount() {
        return rankingInfoList.size();
    }

    /**
     * recyclerview的itemview的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    class RankingViewHolder extends RecyclerView.ViewHolder{
        protected final ImageView image_singer;
        protected final TextView tv_song;
        protected final TextView tv_heat;
        protected final TextView tv_info;
        protected final ImageView image_ranking;

        public RankingViewHolder(View itemView) {
            super(itemView);
            image_singer = (ImageView) itemView.findViewById(R.id.image_ranking_singer);
            tv_song = (TextView) itemView.findViewById(R.id.ranking_song);
            tv_heat = (TextView) itemView.findViewById(R.id.ranking_heat);
            tv_info = (TextView) itemView.findViewById(R.id.ranking_info);
            image_ranking = (ImageView) itemView.findViewById(R.id.ranking);
        }
    }
}
