package com.zilong.demo.changpiandian.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;
import com.squareup.leakcanary.RefWatcher;
import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.application.MyApplication;
import com.zilong.demo.changpiandian.fragment.LocalFragment;
import com.zilong.demo.changpiandian.fragment.MusicLibraryFragment;
import com.zilong.demo.changpiandian.fragment.RankingFragment;
import com.zilong.demo.changpiandian.fragment.BarFragment;
import com.zilong.demo.changpiandian.interfaces.IService;
import com.zilong.demo.changpiandian.service.MusicService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private BottomTabBar mBottomTabBar;
    private Toolbar toolbar;
    private ImageView iv_openDrawer;
    private LinearLayout linear_musiclibrary;
    private LinearLayout linear_xiaoka;
    private LinearLayout linear_ranking;
    private LinearLayout linear_local;
    private BarFragment barFragment;
    private RankingFragment rankingFragment;
    private LocalFragment localFragment;
    private MusicLibraryFragment musicLibraryFragment;
//    private MyConn conn;
    private IService myBinder;
    private MusicService musicService;
    protected ImageView image_play;
    protected MusicService service;
    protected MyApplication.MyConn conn;
    protected DrawerLayout drawer;
    protected NavigationView navigationView;
    protected SlidingPaneLayout spl;
    protected RelativeLayout rel_userInfo;
    protected RelativeLayout rel_Join;
    protected RelativeLayout rel_Msg;
    protected RelativeLayout rel_Works;
    protected RelativeLayout rel_Orders;
    protected RelativeLayout rel_Vip;
    protected RelativeLayout rel_Settings;
    protected EditText edit_tool;
    protected ImageView image_usericon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RefWatcher refWatcher = MyApplication.getRefWatcher();
        refWatcher.watch(this);

        service = MyApplication.getService();
        conn = MyApplication.getConn();
        initView();
        initEvent();

        linear_musiclibrary.setSelected(true);
        selectfragment(R.id.linear_musiclibrary);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(service!=null){
            unbindService(conn);

        }
    }

    private void initEvent() {
        linear_musiclibrary.setOnClickListener(this);
        linear_xiaoka.setOnClickListener(this);
        linear_ranking.setOnClickListener(this);
        linear_local.setOnClickListener(this);
        image_play.setOnClickListener(this);
        iv_openDrawer.setOnClickListener(this);

        rel_userInfo.setOnClickListener(this);
        rel_Join.setOnClickListener(this);
        rel_Msg.setOnClickListener(this);
        rel_Works.setOnClickListener(this);
        rel_Orders.setOnClickListener(this);
        rel_Vip.setOnClickListener(this);
        rel_Settings.setOnClickListener(this);

        image_usericon.setOnClickListener(this);
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setBackgroundResource(R.mipmap.title_720p);
        //toolbar扩展到整个界面，实现与QQ一样的界面
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        iv_openDrawer = (ImageView) findViewById(R.id.pic_openDrawer);
        image_play = (ImageView) findViewById(R.id.image_play);
        edit_tool = (EditText) findViewById(R.id.toolbar_edit);


        //四个导航栏
        linear_musiclibrary = (LinearLayout) findViewById(R.id.linear_musiclibrary);
        linear_xiaoka = (LinearLayout) findViewById(R.id.linear_xiaoka);
        linear_ranking = (LinearLayout) findViewById(R.id.linear_ranking);
        linear_local = (LinearLayout) findViewById(R.id.linear_local);

        spl = (SlidingPaneLayout) findViewById(R.id.slidingPaneLayout);

        //菜单栏
        rel_userInfo = (RelativeLayout) findViewById(R.id.rel_userinfo);
        rel_Join = (RelativeLayout) findViewById(R.id.rel_join);
        rel_Msg = (RelativeLayout) findViewById(R.id.rel_mes);
        rel_Works = (RelativeLayout) findViewById(R.id.rel_works);
        rel_Orders = (RelativeLayout) findViewById(R.id.rel_orders);
        rel_Vip = (RelativeLayout) findViewById(R.id.rel_vip);
        rel_Settings = (RelativeLayout) findViewById(R.id.rel_settings);
        image_usericon = (ImageView) findViewById(R.id.user_icon);
    }

    @Override
    public void onClick(View v) {
        /**
         * 点击了导航就将相对应的fragment添加到相应的容器中。
         * 并且设置选中当前的fragment
         * 这里应该先进行判断，是否为空，如果为空，则创建添加；如果不为空，就显示。
         */
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.linear_musiclibrary:
                linear_musiclibrary.setSelected(true);
                linear_local.setSelected(false);
                linear_ranking.setSelected(false);
                linear_xiaoka.setSelected(false);
                selectfragment(v.getId());
                break;
            case R.id.linear_xiaoka:
                linear_xiaoka.setSelected(true);
                linear_musiclibrary.setSelected(false);
                linear_local.setSelected(false);
                linear_ranking.setSelected(false);
                selectfragment(v.getId());
                break;
            case R.id.linear_ranking:
                linear_ranking.setSelected(true);
                linear_musiclibrary.setSelected(false);
                linear_xiaoka.setSelected(false);
                linear_local.setSelected(false);
                selectfragment(v.getId());
                break;
            case R.id.linear_local:
                linear_local.setSelected(true);
                linear_ranking.setSelected(false);
                linear_musiclibrary.setSelected(false);
                linear_xiaoka.setSelected(false);
                selectfragment(v.getId());
                break;
            case R.id.image_play:
                intent.setClass(this,PlayDetail.class);
                startActivity(intent);
//                Intent intent = new Intent(MainActivity.this, PlayDetail.class);
                break;
            case R.id.pic_openDrawer:
                spl.openPane();
                break;

            /**
             * 菜单栏的点击事件
             */
            case R.id.user_icon:
                intent.setClass(this,LoginActivity.class);
                startActivity(intent);
                spl.closePane();
                break;

            case R.id.rel_userinfo:
                intent.setClass(this,EditPersonalActivity.class);
                startActivity(intent);
                spl.closePane();
                break;
            case R.id.rel_join:
                intent.setClass(this,SingerJoinActivity.class);
                startActivity(intent);
                spl.closePane();
                break;
            case R.id.rel_mes:
                intent.setClass(this,MyMsgActivity.class);
                startActivity(intent);
                spl.closePane();
                break;
            case R.id.rel_works:
                intent.setClass(this,MyWorks.class);
                startActivity(intent);
                spl.closePane();
                break;
            case R.id.rel_orders:
                intent.setClass(this,MyOrdersActivity.class);
                startActivity(intent);
                spl.closePane();
                break;
            case R.id.rel_vip:
                intent.setClass(this,VipActivity.class);
                startActivity(intent);
                spl.closePane();
                break;
            case R.id.rel_settings:
                intent.setClass(this,SettingsActivity.class);
                startActivity(intent);
                spl.closePane();
                break;
        }

//            fragmentTransaction.addToBackStack(null);
//            fragmentTransaction.commit();
    }

    private void selectfragment(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        switch (id) {
            //音乐库
            case R.id.linear_musiclibrary:
                if(musicLibraryFragment==null) {
                    musicLibraryFragment = new MusicLibraryFragment();

                    fragmentTransaction.add(R.id.fg_content, musicLibraryFragment);

                }else {
                    fragmentTransaction.show(musicLibraryFragment);
                }
                break;
            //小咖
            case R.id.linear_xiaoka:
                if(barFragment ==null){
                    barFragment = new BarFragment();
                    fragmentTransaction.add(R.id.fg_content, barFragment);
                }else {
                    fragmentTransaction.show(barFragment);
                }
                break;
            //排行榜
            case R.id.linear_ranking:
                if(rankingFragment==null){
                    rankingFragment = new RankingFragment();
                    fragmentTransaction.add(R.id.fg_content,rankingFragment);
                }else {
                    fragmentTransaction.show(rankingFragment);
                }
                break;
            //我的音乐
            case R.id.linear_local:
                if(localFragment==null){
                    localFragment = new LocalFragment();
                    fragmentTransaction.add(R.id.fg_content,localFragment);
                }else {
                    fragmentTransaction.show(localFragment);
                }
                break;
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if(musicLibraryFragment!=null){
            fragmentTransaction.hide(musicLibraryFragment);
        }
        if(barFragment !=null){
            fragmentTransaction.hide(barFragment);
        }
        if(rankingFragment!=null){
            fragmentTransaction.hide(rankingFragment);
        }
        if(localFragment!=null){
            fragmentTransaction.hide(localFragment);
        }
    }

    private long exitTime = 0;
    /**
     *重写返回按键，按一下提醒，按两下退出应用。
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime>2000)){
                Toast.makeText(MainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 菜单选项的点击事件，点击之后退出菜单
     * @param item
     * @return
     */
}
