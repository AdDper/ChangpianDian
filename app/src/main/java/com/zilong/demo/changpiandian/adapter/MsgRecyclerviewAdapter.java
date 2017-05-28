package com.zilong.demo.changpiandian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.model.Message;

import java.util.List;

/**
 * Created by Administrator on 2017/5/20.
 */

public class MsgRecyclerviewAdapter extends RecyclerView.Adapter<MsgRecyclerviewAdapter.MsgViewHolder> {
    private Context context;
    private List<Message> messageList;

    public MsgRecyclerviewAdapter(Context context, List<Message> messageList) {
        this.context = context;
        this.messageList = messageList;
    }

    @Override
    public MsgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("GOD", "onCreateViewHolder: ");
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, null);
        MsgViewHolder holder = new MsgViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MsgViewHolder holder, int position) {
        Message message = messageList.get(position);
        Log.i("GOD", "onBindViewHolder: "+message.getTime());
        holder.tv_msg_time.setText(message.getTime());
        holder.tv_msg_content.setText(message.getContent());
    }

    @Override
    public int getItemCount() {
        Log.i("GOD", "getItemCount: "+messageList.size());
        return messageList.size();
    }

    class MsgViewHolder extends RecyclerView.ViewHolder{

        protected final TextView tv_msg_time;
        protected final TextView tv_msg_content;

        public MsgViewHolder(View itemView) {
            super(itemView);
            tv_msg_time = (TextView) itemView.findViewById(R.id.msg_time);
            tv_msg_content = (TextView) itemView.findViewById(R.id.msg_content);
        }
    }
}
