package com.xpl.web.controller;

import com.xpl.api.po.UserInfoPO;
import com.xpl.framework.BaseController;
import com.xpl.framework.ErrorCodeConstant;
import com.xpl.framework.ResultView;
import com.xpl.service.UserInfoService;
import com.xpl.framework.util.JedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "redisSet", method = RequestMethod.GET)
    public ResultView<?> redisSet(String key, String value){
        ResultView<?> result = new ResultView<>();

        JedisUtil.set(key, value, 0);

        result.setCode(ErrorCodeConstant.CODE_SUCCESS);
        return result;
    }

    @RequestMapping(value = "testGet", method = RequestMethod.GET)
    public ResultView<?> testGet(UserInfoPO userInfoPO){
        ResultView<?> result = new ResultView<>();

        System.out.println(userInfoPO);

        result.setCode(ErrorCodeConstant.CODE_SUCCESS);
        return result;
    }

    @RequestMapping(value = "testPost", method = RequestMethod.POST)
    public ResultView<?> testPost(HttpServletRequest request, UserInfoPO userInfoPO){
        ResultView<?> result = new ResultView<>();

        String id = request.getParameter("id");

        System.out.println("id为：" + id);

        System.out.println("对象为：" + userInfoPO);

        result.setCode(ErrorCodeConstant.CODE_SUCCESS);
        return result;
    }
}
