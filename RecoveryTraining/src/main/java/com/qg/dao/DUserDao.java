package com.qg.dao;

import com.qg.entity.DUser;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

/**
 * Created by jason on 16-10-18.
 */
public interface DUserDao {
    /**
     * 根据手机和密码，查询返回病患信息
     * 若不存在返回空，若密码不对返回id=0的医生
     * @param phone 医生手机
     * @return
     */
    DUser queryByPhone(@Param("phone") String phone);

    /**
     * 新建一个医生用户
     * @param dUser
     * @return
     */
    boolean save(DUser dUser);

    /**
     * 查询所有的医生用户
     * @return
     */
    ArrayList<DUser> queryAll();

    /**
     * 更新医生
     * @param dUser
     * @return
     */
    boolean updateDUser(DUser dUser);
}
