package com.qg.service.impl;

import com.qg.dao.UserMapper;
import com.qg.entity.User;
import com.qg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户服务实现类
 * Created by symon on 16-12-23.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User login(String username, String password) {
        Map<String, String> map = new HashMap<>(4, 1);
        map.put("username", username);
        map.put("password", password);
        Map<String, Object> mybatisMap = new HashMap<>(2);
        mybatisMap.put("map", map);
        List<User> userList = userMapper.selectByParameters(mybatisMap);
        if(!CollectionUtils.isEmpty(userList)) {
            return userList.get(0);
        } else {
            return null;
        }
    }
}
