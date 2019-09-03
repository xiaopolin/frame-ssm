package com.xpl.util;

import com.alibaba.fastjson.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUtil {

    public String get(String url, JSONObject param){

        String result = "";

        try {
            URL target = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) target.openConnection();
            if (200 != conn.getResponseCode()) {
                throw new Exception("状态码返回错误");
            } else {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                boolean var6 = false;

                int len;
                while (-1 != (len = is.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }

                result = baos.toString("utf-8");
            }
        } catch (Exception var7) {

            System.out.println("错误");

        }

        return result;
    }

    public static void main(String[] args) {
        HttpUtil httpUtil = new HttpUtil();
        String getResult = httpUtil.get("http://www.baidu.com", null);
        System.out.println(getResult);
    }

}
