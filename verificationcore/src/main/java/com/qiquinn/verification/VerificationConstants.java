package com.qiquinn.verification;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/1
 * @Modified By:
 */

public class VerificationConstants
{
    /**
     * 当请求需要身份验证时跳转URL
     */
    public static final String LOGIN_REQUERE_PATH = "/authentication/require";
    /**
     * 默认的用户名密码登录请求处理url
     */
    public static final String LOGIN_PROCESSION_URL = "/authentication/form";
    /* 手机登陆地址 */
    public static final String LOGIN_AUTHNTICATION_PHONE = "/authentication/phone";
    /* 接口调用需要的验证的地址  */
    public static final String IGNORE_PATH = "/code/*";
    /* QQ登陆验证等地值 */
    public static final String LOGIN_QQ_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    /* 请求 QQ登陆token地址 */
    public static final String LOGIN_QQ_TOKEN = "https://graph.qq.com/oauth2.0/token";
    /* session失效后请求界面 */
    public static final String INVALID_SESSION_URL = "/session/invalid";
    /* 默认的用户注册请求处理url */
    public static final String LOGIN_REGIST_URL = "/user/regist";
    /* QQ登陆的provideId */
    public static final String LOGIN_SOCIAL_QQ_PROVIDER_ID = "qq";
    /* QQ登陆的appId */
    public static final String LOGIN_SOCIAL_QQ_APP_ID = "101448999";
    /* QQ登陆的secret */
    public static final String LOGIN_SOCIAL_QQ_APP_SECRET = "1d958787a87559bad371c0a9e26eef61";
    /* 微信登陆的provideId */
    public static final String LOGIN_SOCIAL_WECHAT_PROVIDER_ID = "wechat";
    /* 微信登陆的provideId */
    public static final String LOGIN_SOCIAL_WECHAT_APP_ID = "wxfd6965ab1fc6adb2";
    /* 微信登陆的provideId */
    public static final String LOGIN_SOCIAL_WECHAT_APP_SECRET = "66bb4566de776ac699ec1dbed0cc3dd1";
    /* 默认social登陆拦截地址 */
    public static final String LOGIN_SOCIAL_DEFUALT_FILTER_URL = "/login";
    /* 默认htt请求中携带图片验证码的参数名称 */
    public static final String DEFAULT_PARAMETER_IMAGE_CODE = "iamgeCode";
    /* http请求中默认的携带短信验证码信息的参数的名称 */
    public static final String DEFAULT_PARAMETER_PHONE_CODE = "phoneCode";
    /* 请求手机验证码的传递手机号的参数名称 */
    public static final String DEFUALT_PARAMETER_PHONE_NUMBER= "phone";
    /* 默认密码 */
    public static final String DEFUALT_PASSWORD = "123456";
    /* */
    public static final String JSON_HEARDER = "application/json;charset=UTF-8";



}
