package com.zilong.demo.changpiandian.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.adapter.MsgRecyclerviewAdapter;
import com.zilong.demo.changpiandian.model.Message;

import java.util.ArrayList;

/**
 * 我的消息
 */
public class MyMsgActivity extends AppCompatActivity {

    protected LuRecyclerView mes_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_msg);

        initView();
        initData();
    }

    private void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mes_recyclerview.setLayoutManager(linearLayoutManager);

        ArrayList<Message> messageArrayList = new ArrayList<>();
        Message message = new Message();
        message.setTime("2017/3/10");
        message.setContent("您已购买一张正版专辑，请继续支持正版音乐。");
        messageArrayList.add(message);

        Message message1 = new Message();
        message1.setTime("2016/12/26");
        message1.setContent("您已购买一张正版单曲，请继续支持正版音乐。");
        messageArrayList.add(message1);


   /*     for (int i = 0; i < 10; i++) {
            Message message2 = new Message();
            message2.setTime("2014/7/18");
            message2.setContent("您已购买一张正版专辑，请继续支持正版音乐。");
            messageArrayList.add(message2);
        }*/
        MsgRecyclerviewAdapter msgRecyclerviewAdapter = new MsgRecyclerviewAdapter(this, messageArrayList);
        LuRecyclerViewAdapter adapter = new LuRecyclerViewAdapter(msgRecyclerviewAdapter);
        mes_recyclerview.setAdapter(adapter);
    }

    private void initView() {
        mes_recyclerview = (LuRecyclerView) findViewById(R.id.msg_recyclerview);

    }
}
