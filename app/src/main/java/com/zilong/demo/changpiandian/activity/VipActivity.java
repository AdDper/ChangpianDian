package com.zilong.demo.changpiandian.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.singleclass.PlayIt;

/**
 * 设置-vip
 */
public class VipActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button alipay;
    protected Button wechatpay;
    protected Button unionpay;
    protected ImageView image_return;
    protected ImageView image_play;
    private PlayDetail playDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        alipay.setOnClickListener(this);
        wechatpay.setOnClickListener(this);
        unionpay.setOnClickListener(this);
        image_return.setOnClickListener(this);
        image_play.setOnClickListener(this);
    }

    private void initData() {

    }

    private void initView() {
        alipay = (Button) findViewById(R.id.btn_alipay);
        wechatpay = (Button) findViewById(R.id.btn_wechatpay);
        unionpay = (Button) findViewById(R.id.btn_unionPay);
        image_return = (ImageView) findViewById(R.id.vip_return);
        image_play = (ImageView) findViewById(R.id.vip_play);
    }

    //点击支付
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alipay:
                Toast.makeText(this, "支付宝支付", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_wechatpay:
                Toast.makeText(this, "微信支付", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_unionPay:
                Toast.makeText(this, "银联支付", Toast.LENGTH_SHORT).show();
                break;
            case R.id.vip_return:
                this.finish();
                break;
            case R.id.vip_play:
                playDetail = PlayIt.getInstance();
                Intent intent = new Intent(this, playDetail.getClass());
                startActivity(intent);
                break;
        }
    }
}
