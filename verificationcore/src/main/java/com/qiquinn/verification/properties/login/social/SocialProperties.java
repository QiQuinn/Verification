package com.qiquinn.verification.properties.login.social;

import com.qiquinn.verification.VerificationConstants;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/1
 * @Modified By:
 */

public class SocialProperties
{
    private String filterProcessesUrl = VerificationConstants.LOGIN_SOCIAL_DEFUALT_FILTER_URL;

    private QQProperties qq = new QQProperties();

    private WechatProperties wechat = new WechatProperties();

    public WechatProperties getWechat() {
        return wechat;
    }

    public void setWechat(WechatProperties wechat) {
        this.wechat = wechat;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    public QQProperties getQq() {
        return qq;
    }
    public void setQq(QQProperties qq) {
        this.qq = qq;
    }
}
