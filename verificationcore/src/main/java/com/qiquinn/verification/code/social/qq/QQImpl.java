package com.qiquinn.verification.code.social.qq;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/1
 * @Modified By:
 */

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ
{
    private static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken,String appId)
    {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String openIdUrl=String.format(URL_GET_OPENID,accessToken);
        String result=getRestTemplate().getForObject(openIdUrl,String.class);
        //callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        //先直接截取 这个我们后面重构
        this.openId = StringUtils.substringBetween(result,"\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getQQUserInfo()
    {
        String url = String.format(URL_GET_USERINFO,appId,openId);
        String result = getRestTemplate().getForObject(url,String.class);
        System.out.println(result);
        QQUserInfo qqUserInfo = null;
        try
        {
            qqUserInfo = objectMapper.readValue(result,QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
            return qqUserInfo;
        }
        catch (Exception ex)
        {
            throw new RuntimeException("获取用户信息失败");
        }

    }
}
