package com.qiquinn.verification.properties.login.social;

import org.springframework.boot.autoconfigure.social.SocialProperties;
/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/5
 * @Modified By:
 */

public class WechatProperties extends SocialProperties
{
    private String providerId = "wechat";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
