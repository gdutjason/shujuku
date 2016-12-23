package com.qg.util;

/**
 * Created by wf on 2016/12/23.
 */
public class NotEmptyUtil {

    public static boolean isNotNull(Object... object) {
        for(Object obj : object) {
            if(obj == null || "".equals(obj)) {
                return false;
            }
        }
        return true;
    }
}
