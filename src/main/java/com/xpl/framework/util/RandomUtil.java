package com.xpl.framework.util;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class RandomUtil
{
    /**
     * UUID 生成id的分割线
     */
    public static final String RANDOM_SPLIT = "-";

    public static int NUMBER5_START = 10000; // 随机生成5位的序列号
    public static int NUMBER5_END = 90000; // 随机生成5位的序列号


    public static int NUMBER9_START = 100000000; // 随机生成9位的序列号
    public static int NUMBER9_END = 900000000; // 随机生成9位的序列号

    public static String getCreateSeqNo()
    {
        Random random = new Random();
        int createRandom = random.nextInt(NUMBER5_END) + NUMBER5_START;
        return String.valueOf(createRandom);
    }
    /**
     * 获得时间戳长度的流水号，长度为17位
     *
     * @return
     */
    public static String getSerialNumber() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String serialNumber = sdf.format(new Date());
        return serialNumber;
    }
    /**
     * 获得时间戳长度的流水号，默认长度为22位
     *
     * @return
     */
    public static String getSerialNumber22(String name)
    {
        Random random = new Random();
        // 生成四位随机数(1000<=creteRandom<10000)
        long createRandom = random.nextInt(NUMBER5_START) + NUMBER5_END;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String serialNumber = sdf.format(new Date());
        return name + serialNumber + createRandom;
    }


    /**
     * 获得时间戳长度的流水号，默认长度为26位
     *
     * @return
     */
    public static String getSerialNumber26(String name)
    {
        Random random = new Random();
        // 生成四位随机数(1000<=creteRandom<10000)
        long createRandom = random.nextInt(NUMBER9_START) + NUMBER9_END;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String serialNumber = sdf.format(new Date());
        return name + serialNumber + createRandom;
    }
    /**
     * 获得指定长度的流水号 如果指定长度小于17则会返回17位流水号
     *
     * @param num :长度大于等于17且小于等于49
     * @return
     */
    public static String getSerialNumber(int num)
    {
        // 生成流水号
        String uuid = UUID.randomUUID().toString().replace("-", "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateTime = sdf.format(new Date());
        int timeLength = dateTime.length();
        int difference = num - timeLength;
        int sum = num - timeLength;
        // 如果输入长度小于时间戳长度，或者输入长度大于时间戳和UUID的总长度，则只返回时间戳字符串
        if (difference <= 0 || sum > uuid.length())
        {
            uuid = uuid.substring(0, 0);
        }
        else
        {
            uuid = uuid.substring(0, difference);
        }
        StringBuilder serialNumber = new StringBuilder(dateTime);
        serialNumber.append(uuid);
        return serialNumber.toString();
    }

    /**
     * 
     */
    public synchronized static String randomNumberStr(int pCharCount)
    {
        if (pCharCount < 0)
        {
            pCharCount = 6;
        }
        final int number = 9;
        StringBuffer charValue = new StringBuffer();
        // 生成随机字母串
        for (int i = 0; i < pCharCount; i++)
        {
            char c = (char) (new Random().nextInt(number - 0) + '0');
            charValue.append(String.valueOf(c));
        }
        return charValue.toString();
    }

    public static String randomString32()
    {
        return UUID.randomUUID().toString().replaceAll(RANDOM_SPLIT, StringUtils.EMPTY);
    }

    public static String randomString36()
    {
        return UUID.randomUUID().toString();
    }
    
    public static String getBusinessLog(String businessName){
    	StringBuffer stringBuffer = new StringBuffer(businessName);
    	return stringBuffer.append("@").append(randomString32()).append(":").toString();
    }

}
