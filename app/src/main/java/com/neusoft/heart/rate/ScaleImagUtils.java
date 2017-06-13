package com.neusoft.heart.rate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.net.URL;

/**
 * Created by sxt on 2017/6/13.
 * 自带主角光环
 */
public class ScaleImagUtils {

    public Bitmap getScaledBitmap(String imgUrl, int requestWidth, int requestHeight) {
        BitmapFactory.Options options;
        URL url;
        try {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            url = new URL(imgUrl);
            BitmapFactory.decodeStream(url.openStream());
            int width = options.outWidth;
            int height = options.outHeight;
            int rateWidth = width / requestWidth;
            int rateHeight = height / requestHeight;
            options.inSampleSize = rateWidth > rateHeight ? rateHeight : rateWidth;
            options.inJustDecodeBounds = false;

            return BitmapFactory.decodeStream(url.openStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
