package com.xpl.web.controller;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import com.xpl.entity.constant.CosConstant;
import com.xpl.entity.po.UserInfoPO;
import com.xpl.framework.BaseController;
import com.xpl.entity.constant.ErrorCodeConstant;
import com.xpl.framework.ResultView;
import com.xpl.service.UserInfoService;
import com.xpl.framework.util.JedisUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

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

    @RequestMapping(value = "uploadFile", method = RequestMethod.POST)
    public ResultView<String> uploadFile(String fileName, MultipartFile file){
        ResultView<String> result = new ResultView<>();

        COSCredentials cred = new BasicCOSCredentials(CosConstant.SECRET_ID, CosConstant.SECRET_KEY);
        Region region = new Region(CosConstant.REGION_NAME);
        ClientConfig clientConfig = new ClientConfig(region);
        COSClient cosclient = new COSClient(cred, clientConfig);

        String cosKey = "test/" + fileName;

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        PutObjectRequest putObjectRequest = null;
        String realUrl = "";
        try {
            putObjectRequest = new PutObjectRequest(CosConstant.BUCKET, cosKey, file.getInputStream(), objectMetadata);
            cosclient.putObject(putObjectRequest);

            Date expiration = new Date(new Date().getTime() + 5 * 60 * 10000);
            URL url = cosclient.generatePresignedUrl(CosConstant.BUCKET, cosKey, expiration);
            System.out.println("图片在COS服务器上的url:" + url);
            String urlStr = url.toString();
            realUrl = urlStr.substring(0, urlStr.indexOf("?sign="));
            System.out.println("实际截取后的路径为：" + realUrl);

        } catch (IOException e) {
            e.printStackTrace();
        }

        cosclient.shutdown();

        result.setCode(ErrorCodeConstant.CODE_SUCCESS);
        result.setData(realUrl);
        return result;
    }

}
