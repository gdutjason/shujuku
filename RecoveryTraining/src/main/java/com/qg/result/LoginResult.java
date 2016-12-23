package com.qg.result;

import com.qg.entity.DUser;
import com.qg.entity.PUser;

/**
 * 移动端登陆成功类
 * Created by jason on 16-10-18.
 */
public class LoginResult {
    public int status;//1-成功，0-密码错误，2-不存在该用户
    public PUser pUser;
    public DUser dUser;
}
