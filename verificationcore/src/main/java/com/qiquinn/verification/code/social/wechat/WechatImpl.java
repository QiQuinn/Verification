package com.qiquinn.verification.code.social.wechat;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/5
 * @Modified By:
 */

public class WechatImpl extends AbstractOAuth2ApiBinding implements Wechat
{
    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?openid=";

    public WechatImpl(String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
    }

    @Override
    public WechatUserInfo getWechtUserInfo(String openId)
    {
        String url = URL_GET_USER_INFO + openId;
        String response = getRestTemplate().getForObject(url, String.class);
        if(StringUtils.contains(response, "errcode")) {
            return null;
        }
        WechatUserInfo profile = null;
        try {
            profile = objectMapper.readValue(response, WechatUserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profile;
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }
}
