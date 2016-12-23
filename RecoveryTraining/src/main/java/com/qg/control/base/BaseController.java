package com.qg.control.base;

import com.qg.util.GsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 基类
 * Created by symon on 16-12-23.
 */
public class BaseController {

    public static final String SUCCESS;

    public static final String UN_SUCCESS;

    public static final String UN_LOGIN;

    static {
        Map<String, Boolean> map = new HashMap<>(2, 1);
        map.put("success", true);
        SUCCESS = GsonUtil.gson.toJson(map);
        map.put("success", false);
        UN_SUCCESS = GsonUtil.gson.toJson(map);
        map.remove("success");
        map.put("login", false);
        UN_LOGIN = GsonUtil.gson.toJson(map);
    }
}
