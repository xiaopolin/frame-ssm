package com.xpl.dao;

import com.xpl.api.po.UserInfoPO;
import org.apache.ibatis.annotations.Param;

public interface UserInfoDao {

    public UserInfoPO getById(@Param("id")int id);
}
