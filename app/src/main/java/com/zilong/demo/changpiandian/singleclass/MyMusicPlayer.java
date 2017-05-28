package com.zilong.demo.changpiandian.singleclass;

import android.media.MediaPlayer;

/**
 * Created by Administrator on 2017/5/28.
 */

public class MyMusicPlayer {
        /**
         * 内部类实现单例模式
         * 延迟加载，减少内存开销
         *
         * @author xuzhaohu
         *
         */
        private static class SingletonHolder {
            private static MediaPlayer instance = new MediaPlayer();
        }


        public static MediaPlayer getInstance() {
            return SingletonHolder.instance;
        }

}
