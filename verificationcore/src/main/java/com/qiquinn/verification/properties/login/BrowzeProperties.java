package com.qiquinn.verification.properties.login;

import com.qiquinn.verification.properties.login.LoginResultType;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/29
 * @Modified By:
 */
public class BrowzeProperties
{
    private String loginPage = "/demo-login.html";
    private String signUpUrl = "/regist.html";
    private Integer rememberMeTime = 300;
    private LoginResultType loginResultType = LoginResultType.JSON;
    private SessionPropreties session = new SessionPropreties();

    public SessionPropreties getSession() {
        return session;
    }
    public void setSession(SessionPropreties session) {
        this.session = session;
    }
    public LoginResultType getLoginResultType() {
        return loginResultType;
    }

    public void setLoginResultType(LoginResultType loginResultType) {
        this.loginResultType = loginResultType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public Integer getRememberMeTime() {
        return rememberMeTime;
    }

    public void setRememberMeTime(Integer rememberMeTime) {
        this.rememberMeTime = rememberMeTime;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }
}
