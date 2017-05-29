package com.neusoft.heart.rate.bean;

import java.util.Arrays;

/**
 * Created by dell1 on 2017/5/29.
 */
public class EchartsBarBean {

    public String type1;
    public String type2;
    public String title;
    public String imageUrl;
    public String[] times;
    public float[] data1;
    public float[] data2;


    @Override
    public String toString() {
        return "EchartsBarBean{" +
                "type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", title='" + title + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", times=" + Arrays.toString(times) +
                ", data1=" + Arrays.toString(data1) +
                ", data2=" + Arrays.toString(data2) +
                '}';
    }
}
