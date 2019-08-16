package com.xpl.controller;

import com.xpl.api.po.UserInfoPO;
import com.xpl.framework.BaseController;
import com.xpl.framework.ErrorCodeConstant;
import com.xpl.framework.ResultView;
import com.xpl.service.UserInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("manage")
public class UserInfoController extends BaseController {

    private static final Logger logger = LogManager.getLogger(UserInfoController.class.getName());

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "getById", method = RequestMethod.GET)
    public ResultView<UserInfoPO> getById(int id){
        ResultView<UserInfoPO> result = new ResultView<>();

        System.out.println("sysout接口进入");

        logger.info("logger接口进入");

        UserInfoPO userInfoPO = userInfoService.getById(1);

        result.setCode(ErrorCodeConstant.CODE_SUCCESS);
        result.setData(userInfoPO);
        return result;
    }
}
