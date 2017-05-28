package com.zilong.demo.changpiandian.util;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;

/**
 * Created by 子龙 on 2017/5/3.
 */

public class MusicUtil {

    /**
     * 得到音乐专辑的封面图片
     * @param context 应用环境
     * @param audioId 歌曲的ID
     * @return 专辑封面图片
     */
    public static Bitmap getAlbumCover(Context context, int audioId) {
        String str = "content://media/external/audio/media/" + audioId + "/albumart";
        Uri uri = Uri.parse(str);

        ParcelFileDescriptor pfd = null;
        Bitmap bitmap;

        try
        {
            pfd = context.getContentResolver().openFileDescriptor(uri, "r");
        }
        catch (FileNotFoundException e)
        {
            return null;
        }

        if (pfd != null)
        {
            FileDescriptor fd = pfd.getFileDescriptor();
            bitmap = BitmapFactory.decodeFileDescriptor(fd);
            return bitmap;
        }
        else
        {
            return null;
        }
    }
}
