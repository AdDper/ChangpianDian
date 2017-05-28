package com.zilong.demo.changpiandian.model;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/19.
 */

public class RankingInfo {
    private int image_singer;
    private String tv_song;
    private String tv_heat;
    private String tv_info;
    private int image_ranking;


    public int getImage_singer() {
        return image_singer;
    }

    public void setImage_singer(int image_singer) {
        this.image_singer = image_singer;
    }

    public String getTv_song() {
        return tv_song;
    }

    public void setTv_song(String tv_song) {
        this.tv_song = tv_song;
    }

    public String getTv_heat() {
        return tv_heat;
    }

    public void setTv_heat(String tv_heat) {
        this.tv_heat = tv_heat;
    }

    public String getTv_info() {
        return tv_info;
    }

    public void setTv_info(String tv_info) {
        this.tv_info = tv_info;
    }

    public int getImage_ranking() {
        return image_ranking;
    }

    public void setImage_ranking(int image_ranking) {
        this.image_ranking = image_ranking;
    }
}
