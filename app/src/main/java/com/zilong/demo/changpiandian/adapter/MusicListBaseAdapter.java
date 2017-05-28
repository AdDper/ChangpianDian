package com.zilong.demo.changpiandian.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zilong.demo.changpiandian.R;
import com.zilong.demo.changpiandian.model.MusicMedia;

import java.util.List;

/**
 * Created by 子龙 on 2017/5/2.
 */

public class MusicListBaseAdapter extends BaseAdapter {
    private final LayoutInflater inflater;
    private Context context;
    private List<MusicMedia> musicMediaList;
    private String TAG = "TAG";

    public MusicListBaseAdapter(Context context, List<MusicMedia> musicMediaList) {
        this.context = context;
        this.musicMediaList = musicMediaList;
        inflater = LayoutInflater.from(context);
    }

    /**
     *
     */
    @Override
    public int getCount() {
        Log.i(TAG, "getCount: "+musicMediaList.size());
        return musicMediaList.size();
    }

    /**
     *
     */
    @Override
    public Object getItem(int position) {
        return musicMediaList.get(position);
    }

    /**
     *
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     *
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView ==null){
            convertView= inflater.inflate(R.layout.item_localmusic, null, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_title.setText(musicMediaList.get(position).getMusicName());
        viewHolder.tv_singer.setText(musicMediaList.get(position).getmSingerName());

        return convertView;
    }


    class ViewHolder{
        //歌曲名
        private TextView tv_title;
        //歌手名
        private TextView tv_singer;

        public ViewHolder(View view){
            tv_title = (TextView) view.findViewById(R.id.tv_songName);
            tv_singer = (TextView) view.findViewById(R.id.tv_singerName);
        }
    }


}
