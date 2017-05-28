package com.zilong.demo.changpiandian.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.asynctask.RegisterAsyncTask;
import com.zilong.demo.changpiandian.url.RegisterUrl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.mob.tools.utils.ResHelper.getStringRes;

/**
 * 注册界面的activity
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_userName;
    private EditText edit_reg_yanzhengma;
    private EditText edit_reg_password;
    private Button btn_send;
    private Button btn_register;
    private TextView tv_login_quick;
    private boolean isOk = false;
    private String TAG = "TAG";
//    private static String APPKEY = "14ce7f774ef10";
    private static String APPKEY ="1d689dd3d256d";
    // 填写从短信SDK应用后台注册得到的APPSECRET

//    private static String APPSECRET = "04e0f08d04a22edd307247a4e947c88d";
    private static String APPSECRET = "9dd050b2e45c3612d00a138606a5bacf";

    private int time = 60;
    private boolean flag = true;
    protected TextView tv_prompted_info;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SMSSDK.initSDK(RegisterActivity.this,APPKEY,APPSECRET);

        initView();

        EventHandler eh=new EventHandler(){

            @Override
            public void afterEvent(int event, int result, Object data) {
                Message message = new Message();
                message.arg1 = event;
                message.arg2 = result;
                message.obj = data;
                handler.sendMessage(message);
            }
        };
        SMSSDK.registerEventHandler(eh); //注册短信回调


        initEvent();

    }

    private void initEvent() {
        /**
         * 点击获取验证码
         * 点击注册按钮获取输入的内容，包括账户名、密码、验证码。（验证码的验证是和mob平台的服务端进行验证的，而不是我们自己的服务端）
         * 点击直接登录跳转到登录界面
         *
         * 这里的验证有两个操作，一个是验证验证码的对错，另一个是验证账号密码的对错。
         * 验证的对象还不同，一个是本公司的服务器，另一个是mob的服务端。
         *
         * 点击注册按钮的时候，先进行验证码的对错判断，再根据验证码对错的结果来确定是否要再进行账号密码的对错判断。
         *
         * 进行电话号码和密码的判断是否合理性。
         *
         */
        btn_send.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        tv_login_quick.setOnClickListener(this);
    }

    private void initView() {
        edit_userName = (EditText) findViewById(R.id.edit_reg_username);
        edit_reg_yanzhengma = (EditText) findViewById(R.id.edit_yanzhengma);
        edit_reg_password = (EditText) findViewById(R.id.edit_reg_password);
        btn_send = (Button) findViewById(R.id.send);
        btn_register = (Button) findViewById(R.id.button_register);
        tv_login_quick = (TextView) findViewById(R.id.login_quick);
        tv_prompted_info = (TextView) findViewById(R.id.prompted_info);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                //请求验证码，先验证手机号码是否合理
                if (!TextUtils.isEmpty(edit_userName.getText().toString())) {
                    SMSSDK.getVerificationCode("+86",edit_userName.getText().toString());
                    Toast.makeText(this, "验证码已经发送", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_register:


                String password = edit_reg_password.getText().toString();
                String verify = edit_reg_yanzhengma.getText().toString();
                if (password.isEmpty()) {
                    tv_prompted_info.setVisibility(View.VISIBLE);
                    tv_prompted_info.setText("密码不为空");
                    registerError();
                }else  if (isNumeric(password)|isLetter(password)){
                    tv_prompted_info.setVisibility(View.VISIBLE);
                    tv_prompted_info.setText("密码不能全为数字或字母");
                    registerError();
                }else if (verify.isEmpty()){
                    tv_prompted_info.setText("验证码不能为空");
                }else {
                    SMSSDK.submitVerificationCode("+86",edit_userName.getText().toString(),edit_reg_yanzhengma.getText().toString());
                }



                //验证验证码的对错
                //如果验证码正确，则调用接口与服务器进行交互，注册账号。
                //先验证密码的格式是否正确
                break;
            case R.id.login_quick:

                break;
        }
    }

    //判断是否全是数字
    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }
    //判断是否全为字母
    public boolean isLetter(String str) {
        Pattern pattern = Pattern.compile("[a-z]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    //验证码送成功后提示文字
    private void reminderText() {
        btn_send.setVisibility(View.VISIBLE);
        handlerText.sendEmptyMessageDelayed(1, 1000);
    }

    Handler handlerText =new Handler(){
        public void handleMessage(Message msg) {
            if(msg.what==1){
                if(time>0){
                    /**
                     * 按钮的文本发生变化，同时设置按钮不可用
                     */
                    btn_send.setText(time+"s");
                    btn_send.setEnabled(false);
                    time--;
                    handlerText.sendEmptyMessageDelayed(1, 1000);
                }else{
                    btn_send.setEnabled(true);
                    time = 60;
                    btn_send.setText("重新发送验证码");
                }
            }else{
                /**
                 *验证成功了，根据接口注册，获取数据库返回的数据，将数据设置到个人中心。
                 * 也就是一个数据请求的过程。可以用retrofit框架来完成这个工作。
                 * 现在这里我是用的okhttp来发送请求的，而没有去获取服务端返回的数据。
                 * 目前只是验证注册是否可行。
                 */
                String password = edit_reg_password.getText().toString();
              /*  if (password.isEmpty()) {
                    tv_prompted_info.setVisibility(View.VISIBLE);
                    tv_prompted_info.setText("密码不为空");
                    registerError();
                }
                if (isNumeric(password)|isLetter(password)){
                    tv_prompted_info.setVisibility(View.VISIBLE);
                    tv_prompted_info.setText("密码不能全为数字或字母");
                    registerError();
                }*/
                String phone = edit_userName.getText().toString();
                String url = "http://192.168.1.192:8080/jeesite/appUser/register?registerType=0&phone="+phone+"&password="+password;
                Log.i("OO", "onClick: "+url);
                new RegisterAsyncTask(RegisterActivity.this,tv_prompted_info).execute(url);

            }
        }
    };

    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event="+event);
            //验证成功
            if (result == SMSSDK.RESULT_COMPLETE) {
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功,验证通过
                    Toast.makeText(getApplicationContext(), "验证码校验成功", Toast.LENGTH_SHORT).show();
                    handlerText.sendEmptyMessage(2);
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){//服务器验证码发送成功
                    reminderText();
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                }else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){//返回支持发送验证码的国家列表
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
                }
                //验证失败
            } else {
                if(flag){
                    /**
                     *
                     */
                    registerError();
                    btn_send.setVisibility(View.VISIBLE);
                    Toast.makeText(RegisterActivity.this, "验证码获取失败，请重新获取", Toast.LENGTH_SHORT).show();
                    edit_reg_yanzhengma.requestFocus();
                }else{
                    registerError();
                    ((Throwable) data).printStackTrace();
                    int resId = getStringRes(RegisterActivity.this, "smssdk_network_error");
                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    edit_reg_yanzhengma.selectAll();
                    if (resId > 0) {
                        Toast.makeText(RegisterActivity.this, resId, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

    };


    public void registerError(){
        edit_reg_yanzhengma.setText("");
        edit_reg_password.setText("");
//        btn_send.setText("重新发送验证码");

        /**
         * 这里这样写可以让按钮的字体变为重新发送
         */
        time=0;
        handler.sendEmptyMessage(1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
