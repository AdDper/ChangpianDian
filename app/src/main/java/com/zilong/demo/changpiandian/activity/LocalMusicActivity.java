package com.zilong.demo.changpiandian.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.adapter.MusicListBaseAdapter;
import com.zilong.demo.changpiandian.application.MyApplication;
import com.zilong.demo.changpiandian.model.MusicMedia;
import com.zilong.demo.changpiandian.singleclass.PlayIt;
import com.zilong.demo.changpiandian.service.MusicService;
import com.zilong.demo.changpiandian.uri.MusicUri;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 本地音乐的activity
 */
public class LocalMusicActivity extends AppCompatActivity implements View.OnClickListener{

    private ListView lv_local;
    private RelativeLayout rel_playall;
    private MusicListBaseAdapter adapter;
    private ImageView iv_return;
    private ImageView iv_player;
    private String TAG = "TAG";
    protected MusicService service;
    private ArrayList<String> songAddressList;
    private PlayDetail playDetail;
    protected List<MusicMedia> mediaList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_music);

        service = MyApplication.getService();
        initView();
        initData();
        initEvent();
        Log.i(TAG, "onCreate: 歌曲列表界面创建了");

    }

    private void initView() {
        lv_local = (ListView) findViewById(R.id.lv_localmusic);
        iv_return = (ImageView) findViewById(R.id.local_return);
        iv_player = (ImageView) findViewById(R.id.local_player);
    }

    private void initData() {
        playDetail = PlayIt.getInstance();
        /**
         * 在这里，先获取到本地音乐的信息。将歌曲信息放到集合中去。
         * 再将集合传到看适配器中。通过适配器将数据与控件进行关联。
         * 一首歌的基本信息由歌曲名、歌手名、专辑名、存放地址（播放地址）等。
         * 这里listview展示的是前两个。点击item的点击事件则是播放地址。
         */
        mediaList = scanAllAudioFiles();
        /*绑定适配器*/
        adapter = new MusicListBaseAdapter(this,mediaList);
        lv_local.setAdapter(adapter);
        songAddressList = getSongAddressList();
    }

    /**
     * 将歌曲播放地址放入一个String泛型的集合中
     * 遍历歌曲信息集合，将对应的歌曲的播放地址拿出来放到一个集合中去。
     */
    public ArrayList<String> getSongAddressList(){
        ArrayList<String> songList = new ArrayList<>();
        for (int i = 0; i < mediaList.size(); i++) {
            String musicName = mediaList.get(i).getFilePath();
            songList.add(musicName);
        }
        return songList;
    }

    private void initEvent() {
        iv_return.setOnClickListener(this);
        iv_player.setOnClickListener(this);
        /**
         * 点击了播放列表中的一个item,则播放选中的音乐。
         * 将音乐信息传递给播放界面activity.
         * 歌名、专辑照片。
         */
        lv_local.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MusicMedia itempositionMusic = mediaList.get(position);
                //这里根据listview中的位置来获取点击的item的音乐的播放地址。
                Log.i(TAG, "onItemClick: position is" + position);
/*                String path = itempositionMusic.getFilePath();
                //获取歌名
                String songName = itempositionMusic.getMusicName();
                //获取歌曲id
                int musicId = itempositionMusic.getMusicId();*/

                service.putMusicMediaList(mediaList,position);//传递的是歌曲信息集合
//                service.playMusic(songAddressList,position);//传递的是歌曲播放地址集合
                /**
                 * 这个播放的逻辑要不要在跳转到新界面之后在执行。
                 */
                service.playMusic();
                /**
                 * 将歌曲名以及歌曲的id传递给PlayDetail。
                 * 还有歌曲名的集合和当前播放的歌曲在集合中的位置
                 */
                Intent intent = new Intent(LocalMusicActivity.this,playDetail.getClass());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击了返回，就关闭当前页面
            case R.id.local_return:
                this.finish();
                break;
            /**
             * 这里点击跳转到播放器界面。
             * 应先判断当前有没有播放列表，如果没有，则 点击不会跳转。干脆就不显示这个控件。
             * 我的音乐——本地音乐——歌曲列表——播放列表，前三个每一个都有一个直接进入播放界面的入口。
             * 先从各个界面进入播放界面的入口写起。
             */
            case R.id.local_player:
//                PlayDetail playDetail = PlayIt.getInstance();
                Intent intent = new Intent(LocalMusicActivity.this,playDetail.getClass());
                startActivity(intent);
                break;
        }
    }


    //加载媒体库里的音频。
    public List<MusicMedia> scanAllAudioFiles(){
        Log.i(TAG, "scanAllAudioFiles: its ok");
        List<MusicMedia> list = new ArrayList<>();
        Cursor cursor = getContentResolver().query(MusicUri.video, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                //歌曲编号
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                //歌曲标题
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                //专辑名
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                //专辑ID
                int album_id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
                //歌手名
                String singerName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                //歌手ID
                int artist_id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST_ID));
                //歌曲路径
                String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                //歌曲时长
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                //歌曲大小
                long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));

                int album_key = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_KEY);
                Log.i(TAG, "scanAllAudioFiles: album path is"+album);
                Log.i(TAG, "scanAllAudioFiles: album path is"+String.valueOf(album_key));
                Log.i(TAG, "scanAllAudioFiles: album id is"+String.valueOf(album_id));

                int title_key = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE_KEY));

                //如果大小大于1M
                Log.i(TAG, "scanAllAudioFiles: while is ok");
                if(size>1024*1024){
                    MusicMedia media = new MusicMedia();
                    media.setMusicId(id);
                    media.setMusicName(title);
                    media.setMusicAlbum(album);
                    media.setmAlbuId(album_id);
                    media.setmSingerName(singerName);
                    media.setFilePath(url);
                    media.setMusicDuration(duration);
                    media.setFileSize(String.valueOf(size));
                    media.setSinger_id(artist_id);
                    media.setTitle_key(title_key);
                    media.setAlbum_key(album_key);
                    list.add(media);
                    Log.i(TAG, "scanAllAudioFiles: action"+list.size());
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
