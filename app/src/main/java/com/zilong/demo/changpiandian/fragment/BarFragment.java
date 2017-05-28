package com.zilong.demo.changpiandian.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.github.jdsjlzx.view.CommonFooter;
import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.activity.BarDetail;
import com.zilong.demo.changpiandian.adapter.BarRecyclerviewAdapter;
import com.zilong.demo.changpiandian.model.Xiaoka;

import java.util.ArrayList;

/**
 *小咖
 */

public class BarFragment extends Fragment {
    protected LuRecyclerView bar_recyclerview;
    protected ImageView image_bar_head;
    protected TextView tv_bar_head;
    private LuRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_bar, container, false);
        initView(view);
        initData();
        initEvent();
        return view;
    }

    private void initView(View view) {
        bar_recyclerview = (LuRecyclerView) view.findViewById(R.id.bar_luRecyclerview);
    }

    private void initData() {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        bar_recyclerview.setLayoutManager(manager);
        ArrayList<Xiaoka> xiaokaArrayList = getBarRecyclerviewData();

        BarRecyclerviewAdapter barRecyclerviewAdapter = new BarRecyclerviewAdapter(getContext(), xiaokaArrayList);
        adapter = new LuRecyclerViewAdapter(barRecyclerviewAdapter);

        CommonFooter footer = new CommonFooter(getContext(), R.layout.head_bar);
        initHeadView(footer);
        initHeadData();
        initHeadEvent();
        adapter.addHeaderView(footer);

        bar_recyclerview.setAdapter(adapter);

    }


    /**
     * 实例化头布局的控件
     * @param footer
     */
    private void initHeadView(CommonFooter footer) {
        image_bar_head = (ImageView) footer.findViewById(R.id.bar_head_themeImg);
        tv_bar_head = (TextView) footer.findViewById(R.id.bar_head_themename);
    }

    /**
     * 初始化头布局的数据
     */
    private void initHeadData() {

    }
    /**
     *头布局控件的监听事件
     */
    private void initHeadEvent() {

    }


    /**
     * 返回luRecyclcerview的数据集合
     * @return
     */
    private ArrayList<Xiaoka> getBarRecyclerviewData() {
        ArrayList<Xiaoka> list = new ArrayList<>();
        Xiaoka bar1 = new Xiaoka();
        bar1.setIcon(R.mipmap.bar1);
        bar1.setName("简单爱酒吧");
        list.add(bar1);
        Xiaoka bar2 = new Xiaoka();
        bar2.setIcon(R.mipmap.bar2);
        bar2.setName("不归夜酒吧");
        list.add(bar2);
        Xiaoka bar3 = new Xiaoka();
        bar3.setIcon(R.mipmap.bar3);
        bar3.setName("萧萧酒吧");
        list.add(bar3);
        Xiaoka bar4 = new Xiaoka();
        bar4.setIcon(R.mipmap.bar4);
        bar4.setName("极乐酒吧");
        list.add(bar4);
        return list;
    }

    private void initEvent() {
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(), BarDetail.class);
                startActivity(intent);
            }
        });
    }

}

