package com.zilong.demo.changpiandian.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.zilong.demo.changpiandian.application.MyApplication;
import com.zilong.demo.changpiandian.interfaces.IService;
import com.zilong.demo.changpiandian.model.MusicMedia;
import com.zilong.demo.changpiandian.singleclass.MyMusicPlayer;
import com.zilong.demo.changpiandian.util.TimeUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/5/6.
 */

public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayer;
    private boolean isStoped = true;
    private String TAG = "666";
    protected int duration;
    private boolean isChanging = false;
    private int index;
    private ArrayList<String> currentSongList;
    private int currentPosition;//进度位置
    private int playPosition;
    private int Play_Mode = 1;//设定最初始的播放模式为列表循环。
    private MusicMedia musicMedia = null;
    private List<MusicMedia> musicPlaylist;
    private boolean isPrepared = false;
    private int millis;


    /*发出的广播类型*/
    public static final String ACTION_UPDATE_CURRENT_MUSIC =
            "com.zilong.UPDATE_CURRENT_MUSIC";
    public static final String ACTION_UPDATE_DURATION =
            "com.zilong.UPDATE_DURATION";
    public static final String ACTION_UPDATE_PROGRESS =
            "com.zilong.UPDATE_PROGRESS";
    public static final String ACTION_UPDATE_PLAY_STATUS =
            "com.zilong.UPDATE_PLAY_STATUS";
    public static final String ACTION_UPDATE_PLAY_MODE =
            "com.zilong.UPDATE_PLAY_MODE";

    /*定义整型常量，用于更新UI*/
    private static final int UPDATE_CURRENT_MUSIC = 1;
    private static final int UPDATE_DURATION = 2;
    private static final int UPDATE_PROGRESS = 3;
    private static final int UPDATE_PLAY_STATUS = 4;
    private static final int UPDATE_PLAY_MODE = 5;
    protected LocalBroadcastManager manager;
    private Handler musicHandler = new MusicHandler();

    private MyApplication myApplication;
    private ArrayList<String> songAddressList;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    /**
     * 发送自定义消息
     * @param what 消息的类型
     */
    public void sendMessage(int what)
    {
        Message message = new Message();
        message.what = what;
        musicHandler.sendMessage(message);
    }

    private class MyBinder extends Binder implements IService {

        @Override
        public MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("LOL", "onCreate: first-------------" + duration);
            /*获取本地广播实例*/
        myApplication = (MyApplication) getApplication();
        manager = myApplication.getManager();
    }

    /**
     * 获取当前播放位置
     * @return
     */
/*    public int getCurrentPosition(){
        return mediaPlayer.getCurrentPosition();
    }*/


    public MusicMedia getMusicMedia(){
        return musicMedia;
    }

    /**
     * 更新播放列表。
     * @param musicMediaList
     * @param position
     */
    public void putMusicMediaList(List<MusicMedia> musicMediaList,int position){
        musicPlaylist = musicMediaList;
        playPosition = position;
        musicMedia =  musicMediaList.get(position);
    }


    /**
     * 将歌曲播放地址放入一个String泛型的集合中
     */
    public ArrayList<String> getSongAddressList(){
        ArrayList<String> songList = new ArrayList<>();
        for (int i = 0; i < musicPlaylist.size(); i++) {
            String musicName = musicPlaylist.get(i).getFilePath();
            songList.add(musicName);
        }
        return songList;
    }

    /**
     * 获取歌手名集合
     * @return
     */
    public ArrayList<String> getSingerList(){
        ArrayList<String> singerList = new ArrayList<>();
        for (int i = 0; i < musicPlaylist.size(); i++) {
            String musicName = musicPlaylist.get(i).getmSingerName();
            singerList.add(musicName);
        }
        return singerList;
    }

    /**
     * 获取歌曲名集合。
     * @return
     */
    public ArrayList<String> getSongNameList(){
        ArrayList<String> songNameList = new ArrayList<>();
        for (int i = 0; i < musicPlaylist.size(); i++) {
            String musicName = musicPlaylist.get(i).getMusicName();
            songNameList.add(musicName);
        }
        return songNameList;
    }

    /**
     * 获取mediaplayer对象
     * @return
     */
    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }

    /**
     * 获取播放模式
     * @return
     */
    public int getPlayMode(){
        return Play_Mode;
    }

    /**
     *播放音乐的方法
     * 传入的两个参数，一个是当前播放的歌曲列表的歌曲播放地址的集合。另一个是歌曲在列表中的位置。
     */
/*    public void playMusic(final ArrayList<String> songList, final int position){
        Log.i(TAG, "playMusic: 播放音乐文件的方法");
        Log.i(TAG, "playMusic: 歌曲集合长度"+songList.size());
        currentSongList = songList;
        playPosition = position;
        Log.i(TAG, "playMusic: "+playPosition);
        try {
            mediaPlayer.reset();
            //获取到播放地址
            String path = songList.get(position);
            Log.i(TAG, "playMusic: path:"+path);
            mediaPlayer.setDataSource(path);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 播放本地音乐文件
     */
    public void playMusic(){
            mediaPlayer = new MediaPlayer();
//        mediaPlayer = MyMusicPlayer.getInstance();
            Log.i(TAG, "playMusic: mediaplayer对象"+mediaPlayer.toString());
            songAddressList = getSongAddressList();
            String path = songAddressList.get(playPosition);
            Log.i(TAG, "playMusic: 播放地址为："+path);
            try {
                mediaPlayer.setDataSource(path);
//                mediaPlayer.prepare();
                mediaPlayer.prepareAsync();
                mediaPlayer.setOnPreparedListener(this);
                mediaPlayer.setOnCompletionListener(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    /**
     * 现在的问题是点击下一曲会更新数据，而点击上一曲不会更新数据。
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.i("666", "onPrepared: 音乐文件准备播放的方法");
        mediaPlayer.start();
        duration = musicMedia.getMusicDuration();
        sendMessage(UPDATE_PLAY_STATUS);
        sendMessage(UPDATE_DURATION);
        sendMessage(UPDATE_CURRENT_MUSIC);
        sendMessage(UPDATE_PROGRESS);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
//        mp.stop();
        Log.i(TAG, "onCompletion: 播放完成调用的方法");
//        mp.pause();
//        mp.stop();
        nextSong();
    }


    /**
     * 播放音乐的另一种写法
     */
/*    public void playmusic(int id){
        preparePlay();
    }

    private void preparePlay() {
        if (mediaPlayer!=null){
            mediaPlayer.reset();
            try {
                mediaPlayer.setDataSource("");
                mediaPlayer.prepareAsync();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }*/


    /**
     * 上一曲
     * 我现在需要的是上一曲的播放地址。
     *首先我需要当前的播放歌曲的集合以及当前播放歌曲在集合中的位置
     * 这里还要对当前的位置进行判断，如果为0，则无法上一曲。
     * 如果为歌曲的集合的最大值，则无法进行播放下一曲。
     * 还要根据播放的模式来判断。
     */
    public void lastSong(){
        mediaPlayer.stop();
//        mediaPlayer.release();
        if (Play_Mode == 1) {
            //列表循环
            Log.i(TAG, "lastSong: pp" + playPosition);
            if (playPosition == 0) {
                playPosition = songAddressList.size() - 1;
//                        playMusic(currentSongList,playPosition);
                playMusic();
            } else {
                playPosition--;
                /**
                 * 这里写的是当为0的时候，值为最大下标值。
                 * 否则，值为index--;
                 * 如果我点的就是下标为0的歌曲，那么就成了-1.这显然是不行的。
                 * 所以index--要放在不为0的情况下进行。
                 */
//                        playMusic(currentSongList,playPosition);
                playMusic();
            }
        } else if (Play_Mode == 2) {//单曲循环
            playPosition--;
            if (playPosition == 0) {
                playPosition = songAddressList.size() - 1;
//                        playMusic(currentSongList,playPosition);
                playMusic();
            } else {
//                        playMusic(currentSongList,playPosition);
                playMusic();
            }
        } else {//随机播放
            int np = (int) (Math.random() * songAddressList.size());
//                    playMusic(currentSongList,np);
            playMusic();
        }
        musicMedia = musicPlaylist.get(playPosition);
    }

    /**
     * 下一曲
     * 根据播放模式来决定下一首播放什么
     * 这里要考虑在线播放的情况。所以最好还是统一的用playSong()方法。
     *
     * 现在考虑一种情况，当前模式下点击了下一曲，然后切换了模式
     * 以我这里的逻辑，列表循环和单曲循环都先+1.再根据 之前的模式进行播放。
     */
    public void nextSong(){
        mediaPlayer.stop();
//        mediaPlayer.release();
        Log.i(TAG, "nextSong: 播放下一曲的方法");
            if (mediaPlayer != null) {
                Log.i("LOL", "next--------------------------------");
                //如果是循环列表播放
                if (Play_Mode==1){
                    /**
                     *现在的list集合的长度为5，从0开始的。
                     * 现在这是下一曲，index++;
                     * 如果index = 5,index=0;
                     * 如果index!=5,就直接播放
                     * 行动链条是先点击外面的列表，传进position。
                     * 再根据这个position++**/
                    playPosition++;
                    if (playPosition==songAddressList.size()){
                        //最后一首歌的时候点下一曲，position=3 = 0,播放第一首。
                        //播放第一首的时候，点下一曲，position = 1,播放下一曲。
                        playPosition=0;
                        Log.i(TAG, "nextSong: next song current"+playPosition);
//                        playMusic(currentSongList,playPosition);
                        playMusic();
                    }else if (playPosition<songAddressList.size()){
                        Log.i(TAG, "nextSong: next song current  else:"+playPosition);
//                        playMusic(currentSongList,playPosition);
                        playMusic();
                    }
                }else if (Play_Mode == 2){
                    /**
                     *如果是单曲循环播放,就播放下一曲。
                     * 然后自然播放，歌曲播放完成就继续播放当前歌曲。
                     */
                    playPosition++;
                    if (playPosition==songAddressList.size()){
                        playPosition=0;
                        Log.i(TAG, "nextSong: next song current"+playPosition);
//                        playMusic(currentSongList,playPosition);
                        playMusic();
                    }else if (playPosition<songAddressList.size()){
                        Log.i(TAG, "nextSong: next song current  else:"+playPosition);
//                        playMusic(currentSongList,playPosition);
                        playMusic();
                    }
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.setLooping(true);
                        }
                    });
//                    mediaPlayer.setLooping(true);
                }else {//随机播放
                    playPosition = (int) (Math.random()*songAddressList.size());
//                    playMusic(currentSongList,np);
                    playMusic();
                }
                //如果当前position小于集合总数-1，就可以进行下一曲播放
                //从0开始计数。不等于4就可以加下去。
                musicMedia = musicPlaylist.get(playPosition);
            } else {
                Toast.makeText(this, "mediaplayer为空", Toast.LENGTH_SHORT).show();
            }
    }

    /**
     * 获取当前播放的进度
     */
/*    public int getCurrentTime(){
        if(mediaPlayer!=null){
            currentPosition = mediaPlayer.getCurrentPosition();
        }
        return currentPosition;
    }*/

    /**
     * 获取歌曲总长度,这里获取的只是之前页面点击的歌曲的长度，应该在歌曲
     *状态发生改变时，再实时获取一次 状态。
     * 比如说歌曲播放完成时，点击上一曲下一曲时。
     *
     * 这里我是在上下曲的背景下判断播放模式的。
     * 还有一种就是根据播放模式判断用户操作，如上下曲。
     *
     * 怎么给它判断是已经设置完播放路径了才能进行的呢。
     *
     */

    /**
     * 改变播放模式
     * @param currentMode
     * @return
     */
    public int changeMode(int currentMode){
        Log.i("MODE", "changeMode:currentMode "+currentMode);
        switch (currentMode) {
            //列表循环，点击就切换到单曲循环
            case 1:
                Play_Mode = 2;
                sendMessage(UPDATE_PLAY_MODE);
                 return Play_Mode;
            //单曲循环，点击就切换到随机播放
            case 2:
                Play_Mode = 3;
                sendMessage(UPDATE_PLAY_MODE);
                return Play_Mode;
            //随机播放,点击就切换到列表循环
            case 3:
                Play_Mode = 1;
                sendMessage(UPDATE_PLAY_MODE);
                return Play_Mode;

        }
        Log.i("MODE", "changeMode:play_mode "+Play_Mode);
/*        if (currentMode==1){
            return 2;
        } else if (currentMode == 2) {
            return 3;
        }else {
            return 1;
        }*/
        return 0;
    }

    /**
     * 滚动显示歌词
     */

    /**
     * 通知更新UI，比如标题栏的歌名，歌词，以及背景的专辑图片。
     * @param intent
     * @return
     */



    /**
     * 显示歌曲列表
     * @param intent
     * @return
     */

    /**
     * 将歌曲添加到歌曲列表中，且为当前播放歌曲的下一曲（下一曲播放功能）
     * @param intent
     * @return
     */

    /**
     * 定时关闭音乐
     * @param intent
     * @return
     */


    /**
     * 创建内部类，用于更新UI
     */
    class MusicHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch ( msg.what )
            {
                case UPDATE_CURRENT_MUSIC:
                    updateCurrentMusic();
                    break;

                case UPDATE_DURATION:
                    updateDuration();
                    break;

                case UPDATE_PROGRESS:
                    updateProgress();
                    break;

                case UPDATE_PLAY_STATUS:
                    updatePlayStatus();
                    break;

                case UPDATE_PLAY_MODE:
                    updatePlayMode();
                    break;

                default:
                    break;
            }
        }
    }

    /**
     * 更新播放模式
     */
    private void updatePlayMode() {
        Intent intent = new Intent();
        intent.setAction(ACTION_UPDATE_PLAY_MODE);

        manager.sendBroadcast(intent);
    }

    /**
     * 更新播放进度
     */
    private void updateProgress() {
        if ( mediaPlayer != null && mediaPlayer.isPlaying() )
        {
            Intent intent = new Intent();
            intent.setAction(ACTION_UPDATE_PROGRESS);

            manager.sendBroadcast(intent);
            musicHandler.sendEmptyMessageDelayed(UPDATE_PROGRESS, 10);
        }
    }

    /**
     * 更新播放状态
     */
    private void updatePlayStatus() {

    }

    /**
     * 更新歌曲时长
     */
    private void updateDuration() {
        if ( mediaPlayer != null )
        {
            Intent intent = new Intent();
            intent.setAction(ACTION_UPDATE_DURATION);

            manager.sendBroadcast(intent);
        }
    }

    /**
     * 更新当前播放音乐的相关信息
     */
    private void updateCurrentMusic() {
        Intent intent = new Intent();
        intent.setAction(ACTION_UPDATE_CURRENT_MUSIC);

        manager.sendBroadcast(intent); // 发送本地广播
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}
