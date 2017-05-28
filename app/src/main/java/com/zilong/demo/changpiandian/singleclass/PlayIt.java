package com.zilong.demo.changpiandian.singleclass;

import com.zilong.demo.changpiandian.activity.PlayDetail;

/**
 * Created by Administrator on 2017/5/15.
 */

public class PlayIt {
    /**
     * 内部类实现单例模式
     * 延迟加载，减少内存开销
     *
     * @author xuzhaohu
     *
     */
    private static class SingletonHolder {
        private static PlayDetail instance = new PlayDetail();
    }

    /**
     * 私有的构造函数
     */
    private PlayIt() {

    }

    public static PlayDetail getInstance() {
        return SingletonHolder.instance;
    }

}
