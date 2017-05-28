package com.zilong.demo.changpiandian.util;

import java.text.SimpleDateFormat;

/**
 * Created by 子龙 on 2017/4/27.
 */

public class TimeUtil {
    public static String getTime(int millions){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        String time = simpleDateFormat.format(millions);
        return time;
    }
}
