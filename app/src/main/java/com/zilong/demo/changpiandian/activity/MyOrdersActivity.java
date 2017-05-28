package com.zilong.demo.changpiandian.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.adapter.OrdersRecyclerview;
import com.zilong.demo.changpiandian.model.OrderInfo;

import java.util.ArrayList;

/**
 * 我的订单界面
 */
public class MyOrdersActivity extends AppCompatActivity {

    protected LuRecyclerView orders_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        initView();
        initData();
    }

    private void initData() {
        /**
         * 这里写的是假数据
         */
        ArrayList<OrderInfo> orderInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            OrderInfo orderInfo = new OrderInfo();
            orderInfo.setOrder_image(R.mipmap.song);
            orderInfo.setOrder_albumName("青年晚报");
            orderInfo.setCount(100);
            orderInfo.setPay(2000);
            orderInfos.add(orderInfo);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        orders_recyclerview.setLayoutManager(manager);
        OrdersRecyclerview ordersRecyclerview = new OrdersRecyclerview(this, orderInfos);
        LuRecyclerViewAdapter adapter = new LuRecyclerViewAdapter(ordersRecyclerview);
        orders_recyclerview.setAdapter(adapter);


        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(MyOrdersActivity.this, ItemOrderActivty.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        orders_recyclerview = (LuRecyclerView) findViewById(R.id.orders_luRecyclerview);
    }
}
