package com.zilong.demo.changpiandian.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.LuRecyclerView;
import com.github.jdsjlzx.recyclerview.LuRecyclerViewAdapter;
import com.github.jdsjlzx.view.CommonFooter;
import com.github.jdsjlzx.view.CommonHeader;
import com.squareup.leakcanary.RefWatcher;
import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.activity.MusicManMoreActivity;
import com.zilong.demo.changpiandian.activity.SingerMoreActivity;
import com.zilong.demo.changpiandian.adapter.MlRecyclerviewAdapter;
import com.zilong.demo.changpiandian.adapter.MusicManLuRecyclerviewAdapter;
import com.zilong.demo.changpiandian.adapter.MusicPagerAdapter;
import com.zilong.demo.changpiandian.application.MyApplication;
import com.zilong.demo.changpiandian.interfaces.SukaMusic;
import com.zilong.demo.changpiandian.model.AlbumInfo;
import com.zilong.demo.changpiandian.model.Banner;
import com.zilong.demo.changpiandian.model.MusicLibrary;
import com.zilong.demo.changpiandian.model.Xiaoka;
import com.zilong.demo.changpiandian.interfaces.NetDataRequest;
import com.zilong.demo.changpiandian.url.NetUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * 音乐库
 */

public class MusicLibraryFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ViewPager viewPager_banner;
    private List<Banner.DataBean> banner_data_list = new ArrayList<>();
    protected LinearLayout linear_indicator;
    private List<ImageView> imageList = new ArrayList<>();
    private ArrayList<String> list;

    private int currentPosition = 0;
    /**
     * 自动轮播。
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                viewPager_banner.setCurrentItem(currentPosition,true);
                currentPosition++;
            }
        }
    };
    private Timer timer;
    protected LRecyclerView main_recyclerview;
    protected ImageView image_theme1;
    protected ImageView image_theme2;
    protected TextView musicman_more;
    protected TextView singer_more;
    protected LuRecyclerView mm_recyclerview;
    protected LuRecyclerView s_recyclerview;
    private List<MusicLibrary.DataBean.AlbumlistBean> albumlist = new ArrayList<>();
    private List<MusicLibrary.DataBean.HomePagelistBean> homePagelist = new ArrayList<>();
    private List<MusicLibrary.DataBean.MusicianlistBean> musicianlist = new ArrayList<>();
    private List<MusicLibrary.DataBean.SingerlistBean> singerlist = new ArrayList<>();

    /**
     *
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RefWatcher refWatcher = MyApplication.getRefWatcher();
        refWatcher.watch(this);

        view = inflater.inflate(R.layout.layout_musiclibrary, container, false);
        initView(view);
        initData();
        initEvent();

        return view;
    }

    /**
     * 监听事件
     */
    private void initEvent() {

        viewPager_banner.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             * 在这里完成指示小图标的逻辑
             * @param position
             * @param positionOffset
             * @param positionOffsetPixels
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < list.size(); i++) {
                    linear_indicator.getChildAt(i).setEnabled(false);
                }
                if (position == imageList.size()-1){
                    position = 1;
                }
                if (position == imageList.size()-2){
                    position = 0;
                }
                linear_indicator.getChildAt(position).setEnabled(true);
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case ViewPager.SCROLL_STATE_DRAGGING:
                        if(timer!=null){
                            timer.cancel();
                            timer=null;
                        }
                        break;
                    case ViewPager.SCROLL_STATE_IDLE:
                        //
                        //如果为0，就是跳到4
                        //0 1 2 3 0 1
                        //0_4(0)
                        //1
                      /*  if(currentPosition==0){
                            Log.i("TAG", "onPageScrollStateChanged: idle");
                            viewPager_banner.setCurrentItem(imageList.size()-2,false);
                        }else if(currentPosition == imageList.size()-1){
                            viewPager_banner.setCurrentItem(1,false);
                        }*/

                        if (currentPosition == imageList.size()-1){
//                            viewPager_banner.setCurrentItem(1);
                            currentPosition = 1;
                        }
                        if (currentPosition == imageList.size()-2){
                            currentPosition = 0;
                        }
                        //已经加了1，但是手指拖动了，就停止了定时器。
                        //然后触发下面的代码。
                        if(timer==null){
                            currentPosition--;
                            Log.i("TAG", "onPageScrollStateChanged: "+currentPosition);
                            startTimer();
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initData() {
        initHotAlbum();
    }

    /**
     * 最热专辑的数据初始化,添加头布局尾布局。
     */
    private void initHotAlbum() {
        /**
         * recyclerview与gridlayoutmanager搭配实现一行两个的效果
         */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        main_recyclerview.setLayoutManager(gridLayoutManager);

//        final ArrayList<AlbumInfo> albumInfos = new ArrayList<>();
/*        for (int i = 0; i < 6; i++) {
            AlbumInfo albumInfo = new AlbumInfo();
            albumInfo.setAlbumName("周杰伦的床边故事");
            albumInfo.setSinger("周杰伦");
            albumInfo.setImage_album(R.mipmap.jay);
            albumInfo.setPrice(R.mipmap.price);
            albumInfos.add(albumInfo);
        }*/

        String baseurl ="http://192.168.0.109:8080/jeesite/appHome/";
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(baseurl).addConverterFactory(GsonConverterFactory.create()).build();
        SukaMusic music = retrofit.create(SukaMusic.class);
        Call<MusicLibrary> call = music.gethomeData();
        call.enqueue(new Callback<MusicLibrary>() {
            @Override
            public void onResponse(Call<MusicLibrary> call, Response<MusicLibrary> response) {
                /**
                 * 这里有三个集合（轮播图、音乐人、歌手），两个单数据（两个图片）。
                 */
                albumlist.addAll(response.body().getData().get(0).getAlbumlist());
//                albumlist = response.body().getData().get(0).getAlbumlist();
                homePagelist = response.body().getData().get(0).getHomePagelist();
                musicianlist = response.body().getData().get(0).getMusicianlist();
                singerlist = response.body().getData().get(0).getSingerlist();
            }

            @Override
            public void onFailure(Call<MusicLibrary> call, Throwable t) {
                Toast.makeText(getContext(), ""+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        MlRecyclerviewAdapter mlRecyclerviewAdapter = new MlRecyclerviewAdapter(getContext(),albumlist);
        LRecyclerViewAdapter adapter = new LRecyclerViewAdapter(mlRecyclerviewAdapter);

        /**
         * 添加headerview
         */
        CommonHeader headerView = new CommonHeader(getActivity(),R.layout.musiclibrary_headview);
         //实例化headerview中的控件
        initHeadView(headerView);
         //初始化headerviewz中的数据
        initHeadData();
        adapter.addHeaderView(headerView);

        /**
         * 添加footerview
         */
        CommonFooter footerView = new CommonFooter(getActivity(),R.layout.musiclibrary_footerview);
        initFooterView(footerView);
        initFooterData();
        initFooterEvent();
        adapter.addFooterView(footerView);

        main_recyclerview.setAdapter(adapter);
    }

    /**
     * 尾布局事件监听
     */
    private void initFooterEvent() {
        image_theme1.setOnClickListener(this);
        image_theme2.setOnClickListener(this);
        musicman_more.setOnClickListener(this);
        singer_more.setOnClickListener(this);
    }


    /**
     * 初始化尾布局的数据
     */
    private void initFooterData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),3);
        mm_recyclerview.setLayoutManager(gridLayoutManager);

        /**
         * 模拟数据
         */
        ArrayList<Xiaoka> xiaokaArrayList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Xiaoka xiaoka = new Xiaoka();
            xiaoka.setIcon(R.mipmap.xue);
            xiaoka.setName("薛之谦");
            xiaokaArrayList.add(xiaoka);
        }
/*        MusicManLuRecyclerviewAdapter musicManLuRecyclerviewAdapter = new MusicManLuRecyclerviewAdapter(getContext(), xiaokaArrayList);
        LuRecyclerViewAdapter adapter = new LuRecyclerViewAdapter(musicManLuRecyclerviewAdapter);
        mm_recyclerview.setAdapter(adapter);*/


        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(),3);
        s_recyclerview.setLayoutManager(gridLayoutManager2);

        /**
         *
         * 模拟数据
         */
/*        ArrayList<Xiaoka> singerlist = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Xiaoka xiaoka = new Xiaoka();
            xiaoka.setIcon(R.mipmap.song);
            xiaoka.setName("许嵩");
            singerlist.add(xiaoka);
        }*/

        /**
         * 等他写完。现在首页数据还不完善。
         */
/*        MusicManLuRecyclerviewAdapter singerLRecyclerviewAdapter1 = new MusicManLuRecyclerviewAdapter(getContext(), singerlist);
        LuRecyclerViewAdapter viewAdapter = new LuRecyclerViewAdapter(singerLRecyclerviewAdapter1);

        s_recyclerview.setAdapter(viewAdapter);*/

    }

    /**
     * 实例化尾布局的控件
     * @param footerView
     */
    private void initFooterView(CommonFooter footerView) {
        image_theme1 = (ImageView) footerView.findViewById(R.id.theme1);
        image_theme2 = (ImageView) footerView.findViewById(R.id.theme2);
        musicman_more = (TextView) footerView.findViewById(R.id.more_musicman);
        singer_more = (TextView) footerView.findViewById(R.id.more_singer);
        mm_recyclerview = (LuRecyclerView)footerView.findViewById(R.id.musicman);
        s_recyclerview = (LuRecyclerView) footerView.findViewById(R.id.singer_recyclerview);
    }

    /**
     * Headerview的数据初始化
     */
    private void initHeadData() {
        list = new ArrayList<>();
        list.add("123");
        list.add("21212");
        list.add("21211");
        list.add("1212");
        for (int i = 0; i < list.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(R.drawable.selector_home);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(12, 12);
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            imageView.setLayoutParams(layoutParams);
            linear_indicator.addView(imageView);
            imageView.setEnabled(false);
        }

        for (int i = 0; i < 6; i++) {
            ImageView imageView = new ImageView(getContext());
            imageList.add(imageView);
        }
        viewPager_banner.setAdapter(new MusicPagerAdapter(getContext(), imageList, list));
        viewPager_banner.setCurrentItem(currentPosition);
        startTimer();
    }

    /**
     * 实例化Headview的控件
     * @param headerView
     */
    private void initHeadView(CommonHeader headerView) {
        viewPager_banner = (ViewPager) headerView.findViewById(R.id.musiclibrary_banner);
        linear_indicator = (LinearLayout) headerView.findViewById(R.id.indicator);

    }

    /**
     * 定时器,每三秒轮播图轮播
     */
    private void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                Message msg = Message.obtain();
                msg.what = 1;
                handler.sendEmptyMessage(msg.what);
            }
        }, 0, 3000);
    }

    /**
     * 获取viewpager的数据
     */
    private void initViewPager() {
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl(NetUrl.banner_url).addConverterFactory(GsonConverterFactory.create()).build();
        NetDataRequest netDataRequest = retrofit.create(NetDataRequest.class);
        Call<Banner> bannerData = netDataRequest.getBannerData();
        bannerData.enqueue(new Callback<Banner>() {
            @Override
            public void onResponse(Call<Banner> call, Response<Banner> response) {
                banner_data_list.addAll(response.body().getData());
                /**
                 *  N张轮播图，要写N+2张。
                 *  这个N决定于数据集合的大小，也就是banner_data_list.size()的值。
                 *  图片的初始化以及添加到容器中可以在适配器中完成，但是指示性小图标要在当前的fragment中完成
                 *
                 */
                for (int i = 0; i < banner_data_list.size(); i++) {
                    ImageView imageView = new ImageView(getContext());
                    imageView.setImageResource(R.drawable.selector_home);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
                    layoutParams.leftMargin = 10;
                    layoutParams.rightMargin = 10;
                    imageView.setLayoutParams(layoutParams);
                    linear_indicator.addView(imageView);
                    imageView.setEnabled(false);
                }

                for (int i = 0; i < 6; i++) {
                    ImageView imageView = new ImageView(getContext());
                    imageList.add(imageView);
                }

                viewPager_banner.setAdapter(new MusicPagerAdapter(banner_data_list,getContext(),imageList));
            }

            @Override
            public void onFailure(Call<Banner> call, Throwable t) {

            }
        });
    }

    /**
     * 实例化控件
     * @param view
     */
    private void initView(View view) {
        main_recyclerview = (LRecyclerView) view.findViewById(R.id.main_lrecyclerview);
    }

    /**
     * 布局所有的点击监听事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            //主题1图片
            case R.id.theme1:

                break;
            //主题2图片
            case R.id.theme2:

                break;
            //音乐人更多
            case R.id.more_musicman:
                intent.setClass(getContext(), MusicManMoreActivity.class);
                startActivity(intent);
                break;
            //歌手更多
            case R.id.more_singer:
                intent.setClass(getContext(), SingerMoreActivity.class);
                startActivity(intent);
                break;
        }
    }
}
