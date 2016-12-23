package com.qg.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by jason on 16-10-18.
 */
public class GsonUtil {
    //长期存放一个gson 对象，不进行回收
    public static Gson gson = new Gson();
    static {
        //2016-10-30 15:23:56
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
    }
}
