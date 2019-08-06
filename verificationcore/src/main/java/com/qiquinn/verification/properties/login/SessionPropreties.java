package com.qiquinn.verification.properties.login;

import com.qiquinn.verification.VerificationConstants;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/6
 * @Modified By:
 */
public class SessionPropreties
{
    private Integer maxSessionExisted = 1;

    private Boolean maxSessionPreventLogin;

    private String sessionInvalidUrl = VerificationConstants.INVALID_SESSION_URL;

    public Integer getMaxSessionExisted() {
        return maxSessionExisted;
    }

    public void setMaxSessionExisted(Integer maxSessionExisted) {
        this.maxSessionExisted = maxSessionExisted;
    }

    public Boolean getMaxSessionPreventLogin() {
        return maxSessionPreventLogin;
    }

    public void setMaxSessionPreventLogin(Boolean maxSessionPreventLogin) {
        this.maxSessionPreventLogin = maxSessionPreventLogin;
    }

    public String getSessionInvalidUrl() {
        return sessionInvalidUrl;
    }

    public void setSessionInvalidUrl(String sessionInvalidUrl) {
        this.sessionInvalidUrl = sessionInvalidUrl;
    }
}
