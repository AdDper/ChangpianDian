package com.zilong.demo.changpiandian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.model.OrderInfo;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/22.
 */

public class OrdersRecyclerview extends RecyclerView.Adapter<OrdersRecyclerview.OrdersViewHolder> {
    private Context context;
    private ArrayList<OrderInfo> list;

    public OrdersRecyclerview(Context context, ArrayList<OrderInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public OrdersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, null);
        OrdersViewHolder holder = new OrdersViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(OrdersViewHolder holder, int position) {
        OrderInfo orderInfo = list.get(position);
        holder.orders_album_image.setImageResource(orderInfo.getOrder_image());
        holder.orders_albumName.setText(orderInfo.getOrder_albumName());
        holder.order_buyCount.setText(String.valueOf(orderInfo.getCount()));
        holder.order_payCount.setText(String.valueOf(orderInfo.getPay()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class OrdersViewHolder extends RecyclerView.ViewHolder{

        protected final ImageView orders_album_image;
        protected final TextView orders_albumName;
        protected final TextView order_buyCount;
        protected final TextView order_payCount;

        public OrdersViewHolder(View itemView) {
            super(itemView);
            orders_album_image = (ImageView) itemView.findViewById(R.id.order_image);
            orders_albumName = (TextView) itemView.findViewById(R.id.order_album_name);
            order_buyCount = (TextView) itemView.findViewById(R.id.order_album_salecount);
            order_payCount = (TextView) itemView.findViewById(R.id.order_album_price);
        }
    }
}
