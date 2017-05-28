package com.zilong.demo.changpiandian.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.asynctask.RegisterAsyncTask;

/**
 * 登录界面的activity
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_register;
    protected Button btn_login;
    protected EditText userName;
    protected EditText passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

        initEvent();

    }

    private void initEvent() {
        tv_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
    }

    private void initView() {
        tv_register = (TextView) findViewById(R.id.tv_register);
        btn_login = (Button) findViewById(R.id.button_login);
        userName = (EditText) findViewById(R.id.edit_username);
        passWord = (EditText) findViewById(R.id.edit_reg_password);

    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.button_login:
                String username = userName.getText().toString();
                String password = passWord.getText().toString();
                String url = "http://192.168.1.192:8080/jeesite/appUser/login?phone="+username+"&password="+password;
                new RegisterAsyncTask(this).execute(url);
                break;
        }
    }
}
