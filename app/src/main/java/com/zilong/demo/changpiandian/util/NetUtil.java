package com.zilong.demo.changpiandian.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2017/1/4.
 */

public class NetUtil {
    // 当前是否有网络连接
    public static boolean isNetworkConnected(Context context) {
        //获取网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取当前活动的网络信息
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }


    //获取网络的类型
    public static String getNetworkType(Context context) {
        //获取网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取当前活动的网络信息
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return activeNetworkInfo.getTypeName();
        }
        return null;
    }


    //判断是否是wifi网络
    public  static  boolean isWifi(Context context){
        //获取网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取wifi网络信息
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(wifiNetworkInfo != null && wifiNetworkInfo.isConnected()){
            return true;
        }
        return false;
    }

    //判断是否是移动网络
    public static  boolean  isMobile(Context context){
        //获取网络连接管理器
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取wifi网络信息
        NetworkInfo mobileNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(mobileNetworkInfo != null && mobileNetworkInfo.isConnected()){
            return true;
        }
        return false;
    }

}
