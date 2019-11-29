package com.xpl.service.ServiceImpl;

import com.xpl.entity.po.UserInfoPO;
import com.xpl.dao.UserInfoDao;
import com.xpl.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfoPO getById(int id) {
        System.out.println("正常的impl被调用");
        return userInfoDao.getById(id);
    }
}
