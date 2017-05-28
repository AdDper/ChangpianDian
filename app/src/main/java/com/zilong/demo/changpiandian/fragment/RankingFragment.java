package com.zilong.demo.changpiandian.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.adapter.RankingLRecyclerviewAdapter;
import com.zilong.demo.changpiandian.model.RankingInfo;

import java.util.ArrayList;


/**
 * 排行榜
 */

public class RankingFragment extends Fragment {

    private View view;
    private SwipeRefreshLayout refreshLayout;
    protected LuRecyclerView luRecyclerview;
    protected SwipeRefreshLayout swipeRefreshLayout;

    /**
     *
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_ranking, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }


    private void initEvent() {
        /**
         * 下拉刷新重新加载数据
         */
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                initData();
            }
        });

        /**
         * 上拉加载更多。这个看情况，前期只排十个的话不用写也行。
         */
    }


    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        luRecyclerview.setLayoutManager(linearLayoutManager);

        /**
         * 这里暂且用假数据来查看效果。
         */
        ArrayList<RankingInfo> infoArrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            RankingInfo rankingInfo = new RankingInfo();

            if (i==0){
                rankingInfo.setImage_ranking(1);
            }else if (i == 1){
                rankingInfo.setImage_ranking(2);
            }else if (i == 2){
                rankingInfo.setImage_ranking(3);
            }
            rankingInfo.setImage_singer(R.mipmap.song);
            rankingInfo.setTv_song("山水之间");
            rankingInfo.setTv_heat("100000");
            rankingInfo.setTv_info("创作鬼才许嵩的第五章专辑，词曲都是许嵩一人独立完成");
            infoArrayList.add(rankingInfo);
        }
        RankingLRecyclerviewAdapter rankingLRecyclerviewAdapter = new RankingLRecyclerviewAdapter(getContext(), infoArrayList);
        LuRecyclerViewAdapter adapter = new LuRecyclerViewAdapter(rankingLRecyclerviewAdapter);
        luRecyclerview.setAdapter(adapter);

    }

    private void initView(View view) {
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        luRecyclerview = (LuRecyclerView) view.findViewById(R.id.luRecyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
    }
}
