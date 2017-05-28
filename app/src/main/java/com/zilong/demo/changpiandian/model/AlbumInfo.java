package com.zilong.demo.changpiandian.model;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/19.
 */

public class AlbumInfo {
    private int image_album;
    private String singer;
    private String albumName;
    private int price;

    public int getImage_album() {
        return image_album;
    }

    public void setImage_album(int image_album) {
        this.image_album = image_album;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
