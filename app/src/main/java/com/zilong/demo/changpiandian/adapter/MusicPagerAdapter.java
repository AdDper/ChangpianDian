package com.zilong.demo.changpiandian.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.model.Banner;

import java.util.List;

/**
 * Created by Administrator on 2017/5/17.
 */

public class MusicPagerAdapter extends PagerAdapter {
    private Context context;
    private List<Banner.DataBean> banner_list;
    private int[] images = new int[]{R.mipmap.pro4,R.mipmap.pro1,R.mipmap.pro2,R.mipmap.pro3,R.mipmap.pro4,R.mipmap.pro1};
    private List<ImageView> imageList;

    public MusicPagerAdapter(List<Banner.DataBean> banner_list, Context context, List<ImageView> imageList) {
        this.banner_list = banner_list;
        this.context = context;
        this.imageList = imageList;
    }


    private List<String> test;

    public MusicPagerAdapter(Context context, List<ImageView> imageList, List<String> test) {
        this.context = context;
        this.imageList = imageList;
        this.test = test;
    }

    @Override
    public int getCount() {
        Log.i("TAG", "getCount: "+imageList.size());
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i("TAG", "instantiateItem: "+position);
        ImageView imageView = imageList.get(position);
/*            if (position == 5){
                position = 1;
            }else if (position == 4){
                position = 0;
            }
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            String url = "http://192.168.1.192:8080"+banner_list.get(position).getHomePagePhoto();
            Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
            Log.i("TAG", "instantiateItem: "+banner_list.get(position).getHomePagePhoto());*/
            imageView.setImageResource(images[position]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(imageList.get(position));
    }

}
