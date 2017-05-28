package com.zilong.demo.changpiandian.activity;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.adapter.PlayListBaseAdapter;
import com.zilong.demo.changpiandian.application.MyApplication;
import com.zilong.demo.changpiandian.model.MusicMedia;
import com.zilong.demo.changpiandian.service.MusicService;
import com.zilong.demo.changpiandian.util.ImageUtil;
import com.zilong.demo.changpiandian.util.MusicUtil;
import com.zilong.demo.changpiandian.util.TimeUtil;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 播放界面
 *
 */
public class PlayDetail extends AppCompatActivity implements View.OnClickListener{

    private Toolbar play_toolbr;
    protected RelativeLayout relative_play_detail;
    protected TextView tv_play_songName;
    protected ImageView play_bg;
    protected ImageView play_mode;
    protected ImageView play_last;
    protected ImageView play_or_pause;
    protected ImageView play_next;
    protected ImageView play_list;
    protected TextView time_progress;
    protected TextView time_duration;
    protected SeekBar seekbar_progress;
    private MusicService service;
    private int Cycle_List;
    protected LinearLayout linear_play;
    private ListView lv_playlist;
    protected ImageView playlist_mode_random;
    protected TextView mode_playlist;
    protected TextView tv_pl_songCount;
    protected LinearLayout linear_del_all;
    private PlayListBaseAdapter adapter;
    private PopupWindow popupWindow;
    private MediaPlayer mediaPlayer;
    private boolean isChanging = false;
    private static final String TAG = "RhymeMusic";
    private static final String SUB = "[PlaybackActivity]#";

    private MyApplication application;
    private IntentFilter intentFilter;
    private LocalReceiver localReceiver;
    protected ImageView play_share;
    protected ImageView play_return;
    private  Bitmap bitmap;
    public static final String ACTION_UPDATE_PROGRESS =
            "com.zilong.UPDATE_PROGRESS";
    private Timer timer;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1) {
                seekbar_progress.setProgress(service.getMediaPlayer().getCurrentPosition());

                time_progress.setText(TimeUtil.getTime(service.getMediaPlayer().getCurrentPosition()));
            }
        }
    };
    private MyApplication myApplication;
    private LocalBroadcastManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_detail);
        service = MyApplication.getService();
        mediaPlayer = service.getMediaPlayer();
        initView();
        initData();
        initEvent();
        registerLocalReceiver();
    }

    /**
     * 注册本地广播接收器
     */
    private void registerLocalReceiver()
    {
        Log.d(TAG, SUB + "registerLocalReceiver");
        intentFilter = new IntentFilter();
        intentFilter.addAction(MusicService.ACTION_UPDATE_CURRENT_MUSIC);
        intentFilter.addAction(MusicService.ACTION_UPDATE_DURATION);
        intentFilter.addAction(MusicService.ACTION_UPDATE_PROGRESS);
        intentFilter.addAction(MusicService.ACTION_UPDATE_PLAY_STATUS);
        intentFilter.addAction(MusicService.ACTION_UPDATE_PLAY_MODE);

        localReceiver = new LocalReceiver();
        application = (MyApplication) getApplication();
        /*注册本地广播监听器*/
        application.getManager().registerReceiver(localReceiver, intentFilter);
    }

    /**
     * 内部类
     * 本地广播接收器。
     * 接受来自MusicService的广播，并进行处理
     */
    class LocalReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String action = intent.getAction();

            switch (action)
            {
                case MusicService.ACTION_UPDATE_CURRENT_MUSIC:
                    Log.d(TAG, SUB + "更新音乐标题");
                    updateMusicInfo();
                    break;

                case MusicService.ACTION_UPDATE_DURATION:
                    Log.d(TAG, SUB + "更新音乐时长");
                    updateDuration();
                    break;

                case MusicService.ACTION_UPDATE_PROGRESS:
                    updateProgress();
                    break;

                case MusicService.ACTION_UPDATE_PLAY_STATUS:
                    updateStatus();
                    break;

                case MusicService.ACTION_UPDATE_PLAY_MODE:
                    updatePlayMode();
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 实例化控件
     */
    private void initView() {
        play_share = (ImageView) findViewById(R.id.play_share);
        tv_play_songName = (TextView) findViewById(R.id.play_songName);//歌名
        relative_play_detail = (RelativeLayout) findViewById(R.id.activity_play_detail);
        play_toolbr = (Toolbar) findViewById(R.id.play_toolbar);//toolbar
        play_bg = (ImageView) findViewById(R.id.image_play_bg);//背景图，用来显示专辑图片
        play_return = (ImageView) findViewById(R.id.play_return);

        play_mode = (ImageView) findViewById(R.id.play_mode);//播放模式
        play_last = (ImageView) findViewById(R.id.play_last);//上一首
        play_or_pause = (ImageView) findViewById(R.id.play_or_pause);//播放、暂停
        play_next = (ImageView) findViewById(R.id.play_next);//下一曲
        play_list = (ImageView) findViewById(R.id.play_list);//播放列表

        time_progress = (TextView) findViewById(R.id.time_progress);//当前播放时长
        time_duration = (TextView) findViewById(R.id.time_duration);//歌曲时长

        seekbar_progress = (SeekBar) findViewById(R.id.seek_progress);//进度条
        linear_play = (LinearLayout) findViewById(R.id.linear_play);
    }
    /**
     * 监听事件集合
     */
    private void initEvent() {
        /**
         *各个按钮的点击事件
         */
        play_return.setOnClickListener(this);
        play_share.setOnClickListener(this);
        play_mode.setOnClickListener(this);
        play_last.setOnClickListener(this);
        play_or_pause.setOnClickListener(this);
        play_next.setOnClickListener(this);
        play_list.setOnClickListener(this);
        /**
         * 将这个播放完成事件放到播放界面，而不是放在service中。
         * 要不然会跳过下一首进行播放。
         * 在点击下一曲的时候，有时候会调用setOnCompletionListener()接口。所以造成的结果是跳跃性播放。
         * 改换成OnSeekCompletionListener()接口的话，现在测试是避免了这个问题。
         * 但现在碰到的新问题就是拖动进度条会直接跳到下一首歌。继续测试。
         * 好吧，OnSeekCompletionListener()接口是在点击
         * 进度条也就是完成定位时调用的，所以我在这里写的逻辑就是点击就跳转到下一首。
         *
         *
         * 1.当播放完成的时候会被回调
         * 2.当mMediaPlayer.setDataSource(）;方法没有调用，使用 mMediaPlayer.getDuration()
         * 3.当mMediaPlayer.setDataSource(）;方法没有调用，使用mMediaPlayer.seekto();的时候，
         *等等
         *
         *这里的逻辑是播放完成获取时长，然而我现在是要知道 点击下一曲也能获取到时长。
         * 方法没有被调用
         */
    }

    private void initData() {
        Cycle_List = service.getPlayMode();
        //从本地音乐列表界面进来的话，因为Cycle_List = 1，所以又成了列表循环。
        if (Cycle_List==1){
            play_mode.setImageResource(R.mipmap.mode_cyclelist);
        }else if (Cycle_List==2){
            play_mode.setImageResource(R.mipmap.single_cycle);
        }else {
            play_mode.setImageResource(R.mipmap.random_play);
        }
        /**
         * 设置toolbar可以扩展到窗口。
         */
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams) play_toolbr.getLayoutParams();
        param.setMargins(0, 10, 0, 0);
        play_toolbr.setLayoutParams(param);

        /**
         * 拿到歌曲名以及歌曲的id
         * 这里我是从本地的歌曲列表中拿到了点击播放的歌曲的相关信息。
         * 拿到的信息有：歌名、歌曲的id（拿专辑照片需要这个）、歌曲在本地歌曲列表的位置、
         * 歌曲的播放地址集合、歌手的名字的集合、歌曲名的集合。
         * 这里是不是可以考虑不用intent传值，而是用接口回调的方式传值。
         * 等一下，在之前的歌曲列表页面，点击了就进行了播放。那么是不是在这个页面中可以直接根据这个
         * mediaplayer对象来获取歌曲的相关信息。
         * 歌曲时长、当前播放时长可以直接拿到
         * 但是上一曲、下一曲、播放列表还是需要拿到当前播放列表以及歌曲在当前播放列表的位置。
         *这个位置和列表集合可不可以直接放到service中。
         *
         */

        /**
         *设置seekbar的初始进度及最大进度
         * 为seekbar与播放相关联
         * seekbar的最大进度这里有个疑问，就是歌曲播放在开始播放和结束播放的一小段时间里，
         * 它的滑动块看起来就像没有变动一样。
         * 猜测可能最大 长度不仅仅只是歌曲的时长那么长，而是加上滑动块本身的长度。
         * 等之后来看。
         *
         *
         * 1.当播放完成的时候会被回调
         * 2.当mMediaPlayer.setDataSource(）;方法没有调用，使用 mMediaPlayer.getDuration()
         * 3.当mMediaPlayer.setDataSource(）;方法没有调用，使用mMediaPlayer.seekto();的时候，
         *等等
         * 这里，我先给它注释点，看看是否是因为上述的问题。
         * 现在，验证的下一曲没有问题，不会跳过歌曲。
         * 上一曲还有播放完成进行播放下一曲也进行验证了，没错。
         * 就是因为上述的问题。
         */
        updateProgress();

        updateDuration();

        updateMusicInfo();
    }

    /**
     * 更新进度,现在其他的逻辑基本已经完成，就看这个进度条的了。
     * 现在的问题是，点击暂停后，进度文本就停留在原来暂停的点了。
     */
    public void updateProgress(){
        /**
         * 因为这里我的mediaplayer对象是新建的，所以要先获取mediaplayer对象，再去获取当前播放位置。
         */
        seekbar_progress.setProgress(service.getMediaPlayer().getCurrentPosition());

        time_progress.setText(TimeUtil.getTime(service.getMediaPlayer().getCurrentPosition()));

        seekbar_progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
/*                service.getMediaPlayer().seekTo(progress);
                time_progress.setText(TimeUtil.getTime(service.getMediaPlayer().getCurrentPosition()));
                seekbar_progress.setProgress(progress);*/
                Log.i(TAG, "onProgressChanged: progress的值是"+progress);
                time_progress.setText(TimeUtil.getTime(service.getMediaPlayer().getCurrentPosition()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        }


    /**
     * 更新播放模式
     */
    private void updatePlayMode() {
        if (Cycle_List==1) {
            //切换图片
            play_mode.setImageResource(R.mipmap.mode_cyclelist);
            if (playlist_mode_random!=null&&mode_playlist!=null){
                playlist_mode_random.setImageResource(R.mipmap.mode_cyclelist);
                mode_playlist.setText("列表循环");
            }
        }else if (Cycle_List==2){
            play_mode.setImageResource(R.mipmap.single_cycle);
            if (playlist_mode_random!=null&&mode_playlist!=null){
                playlist_mode_random.setImageResource(R.mipmap.single_cycle);
                mode_playlist.setText("单曲循环");
            }
        }else {
            play_mode.setImageResource(R.mipmap.random_play);
            if (playlist_mode_random!=null&&mode_playlist!=null){
                playlist_mode_random.setImageResource(R.mipmap.random_play);
                mode_playlist.setText("随机播放");
            }
        }
    }

    /**
     * 更新歌曲信息
     * 包括歌名以及专辑的图片。
     */
    private void updateMusicInfo() {
        MusicMedia musicMedia = service.getMusicMedia();
        int musicId = musicMedia.getMusicId();
        String songName = musicMedia.getMusicName();
        tv_play_songName.setText(songName);
        /**
         * 拿到专辑的图片，将图片模糊处理。
         */
        bitmap = MusicUtil.getAlbumCover(getApplicationContext(),musicId);
        if (bitmap == null) {
            play_bg.setImageResource(R.mipmap.beauty);
        } else {
//            Drawable drawable = ImageUtil.BoxBlurFilter(bitmap);
            Bitmap bitmap1 = ImageUtil.rsBlur(this, bitmap, 25);
//            Bitmap bitmap1 = ImageUtil.rsBlur(this, this.bitmap, 0, 1);
//            play_bg.setImageDrawable(drawable);
            play_bg.setImageBitmap(bitmap1);
        }
    }

    /**
     * 更新歌曲总时长
     */
    private void updateDuration() {
//        time_duration.setText(TimeUtil.getTime(service.getMediaPlayer().getDuration()));
//        time_duration.setText(mediaPlayer.getDuration());
        seekbar_progress.setMax(service.getMediaPlayer().getDuration());
        time_duration.setText(TimeUtil.getTime(service.getMediaPlayer().getDuration()));
    }

    /**
     * 更新播放状态
     */
    private void updateStatus() {

    }

    /**
     * 所有控件的点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_return:
                this.finish();
                break;
            case R.id.play_share:
                /**
                 * 分享。第三方分享自带的布局，不用自己写。
                 */
/*                View view_share = View.inflate(PlayDetail.this, R.layout.layout_share, null);
                popupWindow = new PopupWindow(view_share,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));
                //设置点击窗口以外的位置,可以关闭窗口,需要给窗口设置Background
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(linear_play,0,-1000,Gravity.TOP);*/
                break;
            //播放模式
            case R.id.play_mode:
                /**
                 *这里在后期要将播放模式存储起来。这样在下一次启动程序时保证还是上一次设定的模式
                 * 可以用sp存储。
                 * 这里只考虑了按了下一曲的情况，而没有当前歌曲的播放模式情况，比如单曲循环模式。
                 */
                Cycle_List = service.changeMode(Cycle_List);
                if (Cycle_List==1) {
                    //切换图片
                    play_mode.setImageResource(R.mipmap.mode_cyclelist);
                }else if (Cycle_List==2){
                    play_mode.setImageResource(R.mipmap.single_cycle);
                }else {
                    play_mode.setImageResource(R.mipmap.random_play);
                }
                break;
            //上一曲,并将播放状态改为正在播放。
            case R.id.play_last:
                service.lastSong();
                play_or_pause.setImageResource(R.mipmap.pause);
                break;
            //播放/暂停
            case R.id.play_or_pause:
                MediaPlayer mediaPlayer = service.getMediaPlayer();
                if (mediaPlayer!=null&&mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    play_or_pause.setImageResource(R.mipmap.play);
                }else{
                    mediaPlayer.start();
                    play_or_pause.setImageResource(R.mipmap.pause);

                }
                /**
                 * 点击暂停后再播放没有实时的去更新播放时长
                 *
                 */

                break;
            //下一曲，并将播放状态改为正在播放。
            case R.id.play_next:
                Log.i(TAG, "onClick: 哎哟准备哦");
                service.nextSong();
                play_or_pause.setImageResource(R.mipmap.pause);

//                time_duration.setText(TimeUtil.getTime(service.getDuration()));
                break;
            //点击弹出播放列表
            case R.id.play_list:
                View view = View.inflate(PlayDetail.this,R.layout.layout_playlist,null);
                popupWindow = new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                popupWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));
                //设置点击窗口以外的位置,可以关闭窗口,需要给窗口设置Background
                popupWindow.setOutsideTouchable(true);
                popupWindow.showAsDropDown(linear_play,0,-1000,Gravity.TOP);

                /**
                 * 这里我要自定义显示的布局，所以不能用arrayAdapter了。
                 * 改用baseadapter.
                 * 两层嵌套，外层是一个RelativeLayout+listview.
                 * 里面是listview的item的布局
                 */
                initPlayListView(view);
                initPlayListData();
                initPlaylistEvent();

                break;
            //切换播放列表的模式
            case R.id.playlist_random:
                /**
                 * 这里和上面的切换播放模式是一样的
                 */
                Cycle_List = service.changeMode(Cycle_List);
                if (Cycle_List==1) {
                    //切换图片
                    playlist_mode_random.setImageResource(R.mipmap.mode_cyclelist);
                    mode_playlist.setText("列表循环");
                }else if (Cycle_List==2){
                    playlist_mode_random.setImageResource(R.mipmap.single_cycle);
                    mode_playlist.setText("单曲循环");
                }else {
                    playlist_mode_random.setImageResource(R.mipmap.random_play);
                    mode_playlist.setText("随机播放");
                }
                break;
            //清空当前播放列表
            case R.id.linear_del_all:
                /**
                 * 这里要将播放列表清空也就是播放集合清空。
                 * 应该不是在service进行
                 * 这个删除之后界面就自动放下来，而且再点击播放列表的话不会再弹出。
                 * 点击清空列表后应该是停止播放。
                 * 直接销毁当前activity
                 */
                this.finish();
                break;
        }
    }

    /**
     * 初始化播放列表的控件
     * @param view
     */
    private void initPlayListView(View view) {
        lv_playlist = (ListView) view.findViewById(R.id.lv_playlist);
        playlist_mode_random = (ImageView) view.findViewById(R.id.playlist_random);
        mode_playlist = (TextView) view.findViewById(R.id.mode_pl);
        tv_pl_songCount = (TextView) view.findViewById(R.id.playlist_songCount);
        linear_del_all = (LinearLayout) view.findViewById(R.id.linear_del_all);
    }

    /**
     * 初始化播放列表的数据
     */
    private void initPlayListData() {
        /**
         *直接从服务拿到当前播放列表的歌名列表和歌手名列表。
         */
        ArrayList<String> songNameList = service.getSongNameList();
        ArrayList<String> singerList = service.getSingerList();
        adapter = new PlayListBaseAdapter(PlayDetail.this,songNameList, singerList);
        lv_playlist.setAdapter(adapter);
        //设置当前播放列表的歌曲总数
        tv_pl_songCount.setText(String.valueOf(songNameList.size()));

        if (Cycle_List==1){
            playlist_mode_random.setImageResource(R.mipmap.mode_cyclelist);
            mode_playlist.setText("循环播放");
        }else if (Cycle_List==2){
            playlist_mode_random.setImageResource(R.mipmap.single_cycle);
            mode_playlist.setText("单曲循环");
        }else {
            playlist_mode_random.setImageResource(R.mipmap.random_play);
            mode_playlist.setText("随机播放");
        }
    }

    /**
     * 播放列表的点击事件
     */
    private void initPlaylistEvent() {
        playlist_mode_random.setOnClickListener(this);
        linear_del_all.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        Log.i("DY", "onDestroy: 播放界面已被销毁");
        super.onDestroy();
    }

}
