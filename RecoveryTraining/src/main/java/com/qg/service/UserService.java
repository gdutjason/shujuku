package com.qg.service;

import com.qg.entity.User;

/**
 * 用户服务类
 * Created by symon on 16-12-23.
 */
public interface UserService {

    public User login(String username, String password);
}
