package com.xpl.entity.constant;

public class ErrorCodeConstant {

    public static final int CODE_SUCCESS = 0;
    public static final int ERRORCODE_EXCEPTION = -1;
    public static final String ERRORMSG_EXCEPTION = "系统异常";

    public static final int ERRORCODE_UNAUTH = -101;
    public static final String ERRORMSG_UNAUTH = "账号未登录";
    public static final int ERRORCODE_ACCOUNT_UNEXIST = -102;
    public static final String ERRORMSG_ACCOUNT_UNEXIST = "用户不存在";
    public static final int ERRORCODE_PASSWORD_ERROR = -103;
    public static final String ERRORMSG_PASSWORD_ERROR = "密码错误";
    public static final int ERRORCODE_ACCOUNT_LOCK = -104;
    public static final String ERRORMSG_ACCOUNT_LOCK = "账户被锁定";
    public static final int ERRORCODE_SEND_SMS = -105;
    public static final String ERRORMSG_SEND_SMS = "发送短信失败";
    public static final int ERRORCODE_MOBILE_NULL = -106;
    public static final String ERRORMSG_MOBILE_NULL = "未获取到手机号";
    public static final int ERRORCODE_CODE_FAIL = -107;
    public static final String ERRORMSG_CODE_FAIL = "验证码错误";
    public static final int ERRORCODE_MOBILE_EXISTED = -108;
    public static final String ERRORMSG_MOBILE_EXISTED = "手机号已存在";
    public static final int ERRORCODE_EMAIL_EXISTED = -109;
    public static final String ERRORMSG_EMAIL_EXISTED = "邮箱已存在";

}
