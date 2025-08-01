package com.example.chapter8.bean;

import com.example.chapter8.R;

import java.util.ArrayList;

public class GoodsInfo {
    public int id;
    public String name;
    public String description;

    public float price;

    public String picPath;
    public int pic;

    private static String[] mNameArray = {

            "ipone 11",
            "ipone 12",
            "小米10",
            "oppo reno",
            "vivo x30",
            "华为p40 pro",

    };

    private static String[] mDescArray = {
            "Apple 11 那大对比的世界你卡上的那家电脑就看到你",
            "Apple 12 非常好 幸好也好",
            "小米10 很牛逼 很牛逼",
            "oppo reno 很牛逼 很牛逼",
            "vivo x30 很牛逼 很牛逼",
            "华为p40 pro 很牛逼 很牛逼",

    };

    private static float[] mPriceArray = {
            6000,
            7200,
            3999,
            9000,
            1000,
            1200,
    };

    public static int[] mPicArray = {
            R.drawable.iphone_11,
            R.drawable.iphone_12,
            R.drawable.xiaomi_10,
            R.drawable.oppo_reno,
            R.drawable.vivo_x30,
            R.drawable.huawei_p40,
    };

    public static ArrayList<GoodsInfo> getDefaultList(){
        ArrayList<GoodsInfo> list = new ArrayList<>();
        for (int i = 0; i < mNameArray.length; i++) {
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.id = i;
            goodsInfo.name = mNameArray[i];
            goodsInfo.description = mDescArray[i];
            goodsInfo.price = mPriceArray[i];
            goodsInfo.pic = mPicArray[i];
            list.add(goodsInfo);


        }

        return list;
    }


}
