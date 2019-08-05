package com.qiquinn.verification.code.social.wechat;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/5
 * @Modified By:
 */

public class WechatServiceProvider extends AbstractOAuth2ServiceProvider<Wechat>
{
    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WechatServiceProvider(String appId, String appSecret) {
        super(new WechatAuthenTemplate(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public Wechat getApi(String accessToken) {
        return new WechatImpl(accessToken);
    }
}
