package com.zilong.demo.changpiandian.uri;

import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by 子龙 on 2017/5/3.
 */

public class MusicUri {
    public static Uri video= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    public static Uri picture = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
}
