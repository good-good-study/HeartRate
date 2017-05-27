package com.neusoft.heart.rate;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.linechart_bt)
    Button linechartBt;
    @BindView(R.id.barchart_bt)
    Button barchartBt;
    @BindView(R.id.piechart_bt)
    Button piechartBt;
    @BindView(R.id.morechart_bt)
    Button morechartBt;
    @BindView(R.id.chartshow_wb)
    WebView chartshowWb;
    @BindView(R.id.iv_left)
    ImageView ivLeft;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    String TAG;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        initListener();
    }

    private void initView() {
        ButterKnife.bind(this);
    }

    private void initData() {
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("玩儿命加载中...");

        TAG = this.getClass().getName();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initListener() {
        linechartBt.setOnClickListener(this);
        barchartBt.setOnClickListener(this);
        piechartBt.setOnClickListener(this);
        morechartBt.setOnClickListener(this);
        ivLeft.setOnClickListener(this);
        ivRight.setOnClickListener(this);

        //进行webwiev的一堆设置
        chartshowWb.getSettings().setAllowFileAccess(true);
        chartshowWb.getSettings().setJavaScriptEnabled(true);
        chartshowWb.loadUrl("file:///android_asset/echart/myechart.html");
        chartshowWb.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                dialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                //最好在这里调用js代码 以免网页未加载完成
                chartshowWb.loadUrl("javascript:createChart('line');");
                findViewById(R.id.rl_bottom).setVisibility(View.VISIBLE);
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.linechart_bt:
                chartshowWb.loadUrl("javascript:createChart('line');");
                findViewById(R.id.rl_bottom).setVisibility(View.VISIBLE);
                break;
            case R.id.barchart_bt:
                chartshowWb.loadUrl("javascript:createChart('bar');");
                findViewById(R.id.rl_bottom).setVisibility(View.GONE);
                break;
            case R.id.piechart_bt:
                chartshowWb.loadUrl("javascript:createChart('pie');");
                findViewById(R.id.rl_bottom).setVisibility(View.GONE);
                break;
            case R.id.morechart_bt://createMapChart
                chartshowWb.loadUrl("javascript:createChart('more');");
                findViewById(R.id.rl_bottom).setVisibility(View.GONE);
                break;
            case R.id.iv_left:
                dealwithLeft();
                break;
            case R.id.iv_right:
                dealwithRight();
                break;
            default:
                break;
        }
    }

    /**
     * start 和 end 意为拖放的起始点 范围均为  0-100;
     * js中设置的默认初始值 , 和 activity中设置的默认值   两者必须一致, 不然会有错乱
     */
    int start = 20, end = 85;

    private void dealwithLeft() {
        if (start >= 5) {
            start -= 5;
            if (end <= 105 && end >= start + 15) {
                end -= 5;
            }
            chartshowWb.loadUrl("javascript:upZoomState(" + start + "," + end + ");");
        } else {
            Log.i(TAG, "start == " + start + "  end== " + end);
        }
    }

    private void dealwithRight() {
        if (end <= 100) {
            end += 5;
            if (start < end - 15) {
                start += 5;
            }
            chartshowWb.loadUrl("javascript:upZoomState(" + start + "," + end + ");");
        } else {
            Log.i(TAG, "start == " + start + "  end== " + end);
        }
    }
}
