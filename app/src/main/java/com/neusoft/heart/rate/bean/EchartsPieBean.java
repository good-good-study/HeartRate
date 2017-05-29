package com.neusoft.heart.rate.bean;

import android.renderscript.Sampler;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dell1 on 2017/5/29.
 */
public class EchartsPieBean {

    public String type;
    public String title;
    public String[] names;
    public List<Value> values;

    public static class Value {
        public int value;
        public String name;

        @Override
        public String toString() {
            return "Values{" +
                    "value='" + value + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "EchartsPieBean{" +
                "type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", names=" + Arrays.toString(names) +
                ", values=" + values +
                '}';
    }
}
