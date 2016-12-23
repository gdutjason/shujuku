package com.qg.dao;

import com.qg.entity.PUser;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * 病患数据库接口
 * Created by jason on 16-10-17.
 */
public interface PUserDao {

    /**
     * 根据用户手机和密码查询，并返回用户
     * @param phone
     * @return
     */
    PUser queryByPhone(@Param("phone")String phone);

    /**
     * 新建一个病患
     * @param pUser
     * @return
     */
    boolean save(PUser pUser);

    /**
     * 获取所有的病人用户
     * @return
     */
    ArrayList<PUser> queryAll();

    /**
     * 更新病患
     * @param pUser
     * @return
     */
    boolean updatePUser(PUser pUser);
}
