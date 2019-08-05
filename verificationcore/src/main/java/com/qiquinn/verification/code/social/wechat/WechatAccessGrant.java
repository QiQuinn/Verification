package com.qiquinn.verification.code.social.wechat;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/5
 * @Modified By:
 */

public class WechatAccessGrant extends AccessGrant
{
    private static final long serialVersionUID = -7243374526633186782L;
    private String openId;

    public WechatAccessGrant()
    {
        super("");
    }
    public WechatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    public String getOpenId() {
        return openId;
    }
    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
