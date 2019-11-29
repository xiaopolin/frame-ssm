package com.xpl.dao;

import com.xpl.entity.po.UserInfoPO;
import org.apache.ibatis.annotations.Param;

public interface UserInfoDao {

    public UserInfoPO getById(@Param("id")int id);
}
