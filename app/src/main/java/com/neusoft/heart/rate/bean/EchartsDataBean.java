package com.neusoft.heart.rate.bean;

import com.google.gson.Gson;

import java.util.ArrayList;

import static com.neusoft.heart.rate.bean.EchartsPieBean.*;

/**
 * Created by dell1 on 2017/5/29.
 */
public class EchartsDataBean {

    private static Gson gson;
    private static EchartsLineBean lineBean;
    private static EchartsBarBean barBean;
    private static EchartsPieBean pieBean;
    private static EchartsDataBean echartsDataBean;

    private EchartsDataBean() {
    }

    public synchronized static EchartsDataBean getInstance() {
        if (echartsDataBean == null) {
            echartsDataBean = new EchartsDataBean();
            gson = new Gson();
            lineBean = new EchartsLineBean();
            barBean = new EchartsBarBean();
            pieBean = new EchartsPieBean();
        }
        return echartsDataBean;
    }

    public String getEchartsLineJson() {

        lineBean.title = "运动步数统计";
        lineBean.type = "line";
        lineBean.times =
                new String[]{"8:00", "8:10", "8:20", "8:30", "8:40", "8:50", "9:00", "9:10",
                        "9:20", "9:30", "9:40", "9:50", "10:00", "10:10", "10:20", "10:30",
                        "10:40", "10:50", "11:00", "11:10", "11:20", "11:30", "11:40", "11:50",
                        "12:00", "12:10", "12:20", "12:30", "12:40", "12:50", "13:00", "13:10"};
        lineBean.steps =
                new int[]{4, 40, 4, 40, 45, 5, 57, 7, 88, 1, 16, 1, 16, 10, 0, 0, 34, 5, 34, 54, 43,
                        40, 4, 40, 6, 60, 6, 90, 13, 16, 4, 5};

        return gson.toJson(lineBean);
    }

    public String getEchartsBarJson() {
        barBean.title = "上海市蒸发量和降水量";
        barBean.type1 = "蒸发量";
        barBean.type2 = "降水量";
        barBean.times = new String[]{
                "1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"
        };
        barBean.data1 = new float[]{
                2.0f, 4.9f, 7.0f, 23.2f, 25.6f, 76.7f, 135.6f, 162.2f, 32.6f, 20.0f, 6.4f, 3.3f
        };
        barBean.data2 = new float[]{
                2.6f, 5.9f, 9.0f, 26.4f, 28.7f, 70.7f, 175.6f, 182.2f, 48.7f, 18.8f, 6.0f, 2.3f
        };
        return gson.toJson(barBean);
    }

    public String getEchartsPieJson() {
        pieBean.title = "支付宝用户访问来源";
        pieBean.type = "pie";
        pieBean.names = new String[]{"直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎"};
        pieBean.values = new ArrayList<>();
        EchartsPieBean.Value value;
        for (int i = 0; i < pieBean.names.length; i++) {
            value = new EchartsPieBean.Value();
            value.name = pieBean.names[i];
            value.value = 20 * i;
            pieBean.values.add(value);
        }
        return gson.toJson(pieBean);
    }
}
