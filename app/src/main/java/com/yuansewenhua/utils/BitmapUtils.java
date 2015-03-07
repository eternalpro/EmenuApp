package com.yuansewenhua.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;

/**
 * Created by fangshuai on 2014-11-25-0025.
 */
public class BitmapUtils {

    /**
     * 加载Bitmap
     *
     * @param scalSize 缩放比
     * @return
     */
    public static Bitmap loadResBitmap(InputStream in, int scalSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = scalSize;
        Bitmap bitmap = BitmapFactory.decodeStream(in, null, options);
        return bitmap;
    }

    /**
     * 加载Bitmap
     *
     * @param scalSize 缩放比
     * @return
     */
    public static Bitmap loadResBitmap(String path, int scalSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = scalSize;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options);
        return bitmap;
    }
}
