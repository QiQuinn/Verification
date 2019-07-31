package com.qiquinn.verification.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/29
 * @Modified By:
 */
public class BrowzeProperties
{
    private String loginPage = "/demo-login.html";
    private Integer rememberMeTime = 300;
    private LoginResultType loginResultType = LoginResultType.JSON;

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
}
