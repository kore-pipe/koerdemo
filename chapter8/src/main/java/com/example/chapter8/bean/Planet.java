package com.example.chapter8.bean;

import com.example.chapter8.R;

import java.util.ArrayList;
import java.util.List;

public class Planet {

    public int image;
    public String name;
    public String desc;

    public Planet(int image, String name, String desc) {
        this.image = image;
        this.name = name;
        this.desc = desc;
    }

    public Planet() {

    }

    private  static final int[] iconArray = {
            R.drawable.diqiu,
            R.drawable.huoxing,
            R.drawable.jinxing,
            R.drawable.muxing,
            R.drawable.tuxing,
            R.drawable.shuixing

    };

    private static final String[] starArray = {
            "地球",
            "火星",
            "金星",
            "木星",
            "土星",
            "水星"
    };

    private static String[] descArray = {
            "地球是太阳系八大行星之一，也是太阳系中唯一一个自转的行星。",
            "火星是太阳系八大行星之一，也是太阳系中唯一一个非自转的行星。",
            "金星是太阳系八大行星之一，也是太阳系中唯一一个最接近太阳的行星。",
            "木星是太阳系八大行星之一，也是太阳系中唯一一个最远距离的行星。",
            "土星是太阳系八大行星之一，也是太阳系中唯一一个最矮的行星。",
            "水星是太阳系八大行星之一，也是太阳系中唯一"};

    public static List<Planet> getDefaultList(){
        List<Planet> planetList = new ArrayList<>();
        for (int i = 0; i < iconArray.length; i++) {
            planetList.add(new Planet(iconArray[i],starArray[i],descArray[i]));
        }

        return planetList;
    }



}
