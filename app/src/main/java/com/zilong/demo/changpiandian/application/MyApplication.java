package com.zilong.demo.changpiandian.application;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.zilong.demo.changpiandian.interfaces.IService;
import com.zilong.demo.changpiandian.service.MusicService;
import com.zilong.demo.changpiandian.util.NetUtil;

/**
 * Created by Administrator on 2017/5/8.
 */

public class MyApplication extends Application {
    private String TAG = "TAG";
    private IService myBinder;
    private static MusicService musicService;
    private static MyConn myConn;
    private static RefWatcher refWatcher;
//    private NetworkConnectBroadcastReceiver networkConnectBroadcastReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);

        Intent intent = new Intent(this, MusicService.class);
        myConn = new MyConn();
        boolean isOk = bindService(intent,myConn, BIND_AUTO_CREATE);
        if (isOk) {
            Log.i(TAG, "onCreate: "+"绑定服务成功");
        }else{
            Toast.makeText(musicService, "绑定服务失败", Toast.LENGTH_SHORT).show();
        }

//        LeakCanary.install(this);
/*
        networkConnectBroadcastReceiver = new NetworkConnectBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        //网络状态发生改变的广播
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkConnectBroadcastReceiver,intentFilter);*/
    }
    public static RefWatcher getRefWatcher(){
        return refWatcher;
    }

/*
    class  NetworkConnectBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //获取当前的网络状态并显示
            //判断网络是否连接
            // boolean networkConnected = ConnectUtils.isNetworkConnected(context);
            String networkType = NetUtil.getNetworkType(context);
            Log.i("AAA", "onReceive: " + networkType);
            if(NetUtil.isWifi(context)){

            }else if(NetUtil.isMobile(context)){

            }else{

            }


        }
    }*/


    public LocalBroadcastManager getManager()
    {
        return LocalBroadcastManager.getInstance(this); // 获取实例
    }


    public class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (IService) service;
            musicService = myBinder.getService();
            Log.i(TAG, "onServiceConnected: "+"成功连接");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public static MusicService getService(){
        return musicService;
    }

    public static MyConn getConn(){
        return myConn;
    }




}
