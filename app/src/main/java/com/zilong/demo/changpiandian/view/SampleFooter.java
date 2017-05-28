package com.zilong.demo.changpiandian.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.zilong.demo.changpiandian.R;

/**
 * Created by Administrator on 2017/5/24.
 */

public class SampleFooter  extends RelativeLayout{

    public SampleFooter(Context context) {
        super(context);
        init(context);
    }

    public SampleFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SampleFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context){
       inflate(context,R.layout.musiclibrary_footerview,this);
    }



}
