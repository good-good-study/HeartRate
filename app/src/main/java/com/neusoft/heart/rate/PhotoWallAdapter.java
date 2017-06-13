package com.neusoft.heart.rate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.Loader;
import android.support.v4.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;

/**
 * Created by sxt on 2017/6/13.
 * 自带主角光环
 */
public class PhotoWallAdapter extends ArrayAdapter<String> implements AbsListView.OnScrollListener {

    private final GridView mPhotoWall;
    private final Context mContext;
    private final String[] mdatas;
    private final HashSet<TaskWorker> mTaskCollection;
    private final LruCache<String, Bitmap> lruCache;
    private int mfirstVisibleItem;
    private int mvisibleItemCount;
    boolean isFirstEnter = true;

    public PhotoWallAdapter(Context context, int textViewResourceId, String[] datas, GridView photoWall) {
        super(context, textViewResourceId, datas);

        mPhotoWall = photoWall;
        mContext = context;
        mdatas = datas;
        mTaskCollection = new HashSet<TaskWorker>();
        //获取应用程序最大可用内存  设置图片缓存大小为程序最大可用内存的1/8
        int cacheSize = (int) (Runtime.getRuntime().maxMemory()/5);
        lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
        mPhotoWall.setOnScrollListener(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView == null) {
            view = View.inflate(getContext(), R.layout.item, null);
        } else {
            view = convertView;
        }
        ImageView photoView = (ImageView) view.findViewById(R.id.item_photo);
        //给ImageView设置一个tag 防止乱序
        photoView.setTag(mdatas[position]);
        setImage(mdatas[position], photoView);

        return view;
    }

    /**
     * 设置图片前 先在内存中查找,以免重复请求网络 重复创建bitmap对象
     * 否则,就先用一张空图片替代
     *
     * @param url       当前位置的url
     * @param photoView 当前条目的ImageViwe
     */
    private void setImage(String url, ImageView photoView) {
        Bitmap bitmap = getBitmapFromMemoryCache(url);
        if (bitmap != null) {
            photoView.setImageBitmap(bitmap);
        } else {
            photoView.setImageResource(R.mipmap.ic_launcher);
        }
    }

    private Bitmap getBitmapFromMemoryCache(String url) {
        return lruCache.get(url);
    }

    private void addBitmapToMemoryCache(String imageUrl, Bitmap bitmap) {
        if (getBitmapFromMemoryCache(imageUrl) == null) {
            lruCache.put(imageUrl, bitmap);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (scrollState == SCROLL_STATE_IDLE) {//只有在静止时 才加载图片
            loadBitmaps(mfirstVisibleItem, mvisibleItemCount);
        } else {
            cancleAllTasks();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mfirstVisibleItem = firstVisibleItem;
        mvisibleItemCount = visibleItemCount;
        //因为第一次加载时 该方法并不会调用 所以在第一次加载时 手动加载图片
        if (isFirstEnter && visibleItemCount > 0) {
            loadBitmaps(mfirstVisibleItem, mvisibleItemCount);
            isFirstEnter = false;
        }
    }

    /**
     * 请求网络,加载图片
     *
     * @param mfirstVisibleItem 当前屏幕中GridView可见的第一个条目
     * @param mvisibleItemCount 当前屏幕中GridView可见条目的总数
     */
    private void loadBitmaps(int mfirstVisibleItem, int mvisibleItemCount) {
        for (int i = mfirstVisibleItem; i < mvisibleItemCount; i++) {
            String url = mdatas[i];
            Bitmap memoryCache = getBitmapFromMemoryCache(url);
            if (memoryCache == null) {
                TaskWorker taskWorker = new TaskWorker();
                mTaskCollection.add(taskWorker);
                taskWorker.execute(url);
            } else {
                ImageView img = (ImageView) mPhotoWall.findViewWithTag(url);
                if (img != null) {
                    img.setImageBitmap(memoryCache);
                }
            }
        }
    }

    /**
     * 取消当前所有的正在执行的异步任务
     */
    public void cancleAllTasks() {
        for (TaskWorker taskWorker : mTaskCollection) {
            taskWorker.cancel(false);
        }
    }

    class TaskWorker extends AsyncTask<String, Void, Bitmap> {

        private String imageUrl;

        @Override
        protected Bitmap doInBackground(String... strings) {

            imageUrl = strings[0];
            Bitmap bitmap = downloadBitmap(imageUrl);
            if (bitmap != null) {
                addBitmapToMemoryCache(imageUrl, bitmap);
            }
            return bitmap;
        }

        private Bitmap downloadBitmap(String imageUrl) {
            return new ScaleImagUtils().getScaledBitmap(imageUrl, dip2px(mContext, 90), dip2px(mContext, 90));
        }

        /**
         * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
         */
        private int dip2px(Context context, float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }

        /*private Bitmap downloadBitmap(String imageUrl) {
            HttpURLConnection http = null;
            Bitmap bitmap = null;
            try {
                URL url = new URL(imageUrl);
                http = (HttpURLConnection) url.openConnection();
                //http.setRequestMethod("GET");
                http.setConnectTimeout(5 * 1000);
                http.setReadTimeout(5 * 1000);
                if (http.getResponseCode() == 200) {
                    bitmap = BitmapFactory.decodeStream(http.getInputStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (http != null) {
                    http.disconnect();
                }
            }
            return bitmap;
        }*/

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);

            ImageView iv = (ImageView) mPhotoWall.findViewWithTag(imageUrl);
            if (iv != null && bitmap != null) {
                iv.setImageBitmap(bitmap);
            } else if (iv != null) {
                iv.setImageResource(R.mipmap.index);
            }
            //任务完成 从集合中移除当前任务
            mTaskCollection.remove(this);
        }
    }

}
