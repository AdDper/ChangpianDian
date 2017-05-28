package com.zilong.demo.changpiandian.activity;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.zilong.demo.changpiandian.R;

/**
 * 设置页面
 */
public class SettingsActivity extends AppCompatActivity {

    protected Switch settings_wifi;
    protected Switch settings_useplay;
    protected Switch settings_netdownload;
    protected Switch settings_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initView();
        initData();
        initEvent();
    }


    private void initView() {
        settings_wifi = (Switch) findViewById(R.id.switch_onlywifi);
        settings_useplay = (Switch) findViewById(R.id.switch_usenet);
        settings_netdownload = (Switch) findViewById(R.id.switch_netdownload);
        settings_update = (Switch) findViewById(R.id.switch_auto_update);
    }


    private void initData() {
        /**
         * 获取到之前的设置的信息。
         */
        SharedPreferences preferences = getSharedPreferences("settings", MODE_PRIVATE);
        //这里的默认值设置为false
        boolean wifi_isChecked = preferences.getBoolean("wifi", false);
        settings_wifi.setChecked(wifi_isChecked);
        boolean netplay_isChecked = preferences.getBoolean("netplay", false);
        settings_useplay.setChecked(netplay_isChecked);
        boolean netdownload_isChecked = preferences.getBoolean("netdownload", false);
        settings_netdownload.setChecked(netdownload_isChecked);
        boolean auto_update_isChecked = preferences.getBoolean("auto_update", false);
        settings_update.setChecked(auto_update_isChecked);


    }

    /**
     * 切换按钮的选中事件。注意的是：每次进入设置界面，之前设置的状态就没有了，所以要将状态进行保存。
     * 这样进入之前先判断之前的状态。
     * 设置切换的内容也要进行保存，以便其他地方需要用到设置的时候直接拿。
     * 这个直接存在sp存储中就行了。
     *
     * 具体逻辑：
     * 仅wifi联网————其他状态不能播放也不能进行下载操作。确切的说，不能加载一切联网数据。显示无网络图片
     *
     * 使用2G/3G/4G在线播放————如果为否，则运营商网络状态下不能进行播放。
     *                        在歌曲页面，点击歌曲名————获取设置————获取网络状态——————如果为WIFI————播放
     *                                                                      ——————如果为网络————设置为是————播放
     *                                                                                     ————设置为否————不播放
     *
     * 使用2G/3G/4G下载————如果为否，就不能下载。只能等待网络切换到WIFI。下载队列一直保留。
     *                      在歌曲页面，点击下载————获取设置————再获取网络状态—————如果为网络且设置为是————可以下载
     *                                                                    ——————如果为网络且设置为否——————不可以下载
     *                                                                    ——————如果为WIFI，正常下载
     *
     * WIFI下自动更新————MainActivity检测到WIFI在线，然后检测是否有最新的版本，如果有，进行下载更新。
     *
     *
     * 先写个网络检测的工具类。
     * 上面的逻辑有个问题存在，
     * 比如说，不能使用运营商网络进行播放————点击的时候是用的WIFI，等到下一曲的时候切换为网络。这时候应该是不能播放的。
     *                                                                  那这时候是不是说下一曲的逻辑里面也增加判断。
     *
     *                       如果换一种方法，把这些放到application中去，也不行。
     *
     *                       这个主要就是广播对网络状态的一个监听。是不是可以放到服务中去。在线播放的逻辑放到播放音乐的方法里去。
     *                                                                               网络下载的逻辑放到下载音乐的方法里去。
     *
     */
    private void initEvent() {
        final SharedPreferences settings = this.getSharedPreferences("settings", MODE_PRIVATE);
        final SharedPreferences.Editor editor = settings.edit();
        settings_wifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    editor.putBoolean("wifi",true);
                }else {
                    editor.putBoolean("wifi",false);
                }
                    editor.commit();
            }
        });

        settings_useplay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean("netplay",true);
                }else {
                    editor.putBoolean("netplay",false);
                }
                editor.commit();
            }
        });

        settings_netdownload.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean("netdownload",true);
                }else {
                    editor.putBoolean("netdownload",false);
                }
                editor.commit();
            }
        });
        settings_update.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editor.putBoolean("auto_update",true);
                }else {
                    editor.putBoolean("auto_update",false);
                }
                editor.commit();
            }
        });
    }
}
