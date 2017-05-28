package com.zilong.demo.changpiandian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zilong.demo.changpiandian.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/5/12.
 */

public class PlayListBaseAdapter extends BaseAdapter {
    protected final LayoutInflater inflater;
    private Context context;
    private ArrayList<String> songNameList;
    private ArrayList<String> singerList;

    public PlayListBaseAdapter(Context context, ArrayList<String> songNameList, ArrayList<String> singerList) {
        this.context = context;
        this.songNameList = songNameList;
        this.singerList = singerList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return songNameList.size();
    }

    @Override
    public Object getItem(int position) {
        return songNameList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = inflater.inflate(R.layout.item_playlist,null,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_songName.setText(songNameList.get(position));
        /**
         * 这里我要设置歌手的信息。首先我需要一个歌手信息的集合。
         *
         */
        viewHolder.tv_singerName.setText(singerList.get(position));
        return convertView;
    }

    class ViewHolder{
        private TextView tv_singerName;
        private TextView tv_songName;

        public ViewHolder(View view){
            tv_songName = (TextView) view.findViewById(R.id.playlist_item_songName);
            tv_singerName = (TextView) view.findViewById(R.id.playlist_item_singerName);
        }
    }
}
