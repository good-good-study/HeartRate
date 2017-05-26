package com.neusoft.heart.rate;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
    }

    private void initListener() {
        linechartBt.setOnClickListener(this);
        barchartBt.setOnClickListener(this);
        piechartBt.setOnClickListener(this);
        morechartBt.setOnClickListener(this);
        ivLeft.setOnClickListener(this);
        ivRight.setOnClickListener(this);

        //进行webwiev的一堆设置
        //开启本地文件读取（默认为true，不设置也可以）
        chartshowWb.getSettings().setAllowFileAccess(true);
        //开启脚本支持
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
                chartshowWb.loadUrl("javascript:createChart('line',0,100);");
                break;
            case R.id.barchart_bt:
                chartshowWb.loadUrl("javascript:createChart('bar',0,100);");
                break;
            case R.id.piechart_bt:
                chartshowWb.loadUrl("javascript:createChart('pie',0,100);");
                break;
            case R.id.morechart_bt://createMapChart
                chartshowWb.loadUrl("javascript:createChart('more',0,100);");
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

    int start = 0, end = 100;

    private void dealwithLeft() {
        if (end >= 10 && end <= 100) {
            end -= 10;
            chartshowWb.loadUrl("javascript:createChart('line'," + start + "," + end + ",100);");
        } else {
            end = 0;
            Toast.makeText(MainActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
        }
    }

    private void dealwithRight() {
        if (start < 100) {
            start += 10;
            chartshowWb.loadUrl("javascript:createChart('line'," + start + "," + end + ",100);");
        } else {
            start = 0;
            Toast.makeText(MainActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
        }
    }
}
