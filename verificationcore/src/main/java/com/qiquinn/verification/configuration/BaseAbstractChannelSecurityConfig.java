package com.qiquinn.verification.configuration;

import com.qiquinn.verification.VerificationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/1
 * @Modified By:
 */
public class BaseAbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.formLogin()
                .loginPage(VerificationConstants.LOGIN_REQUERE_PATH)  //登陆请求
                .loginProcessingUrl(VerificationConstants.LOGIN_PROCESSION_URL)
                .successHandler(authenticationSuccessHandler)      //登陆成功处理器
                .failureHandler(authenticationFailureHandler);     //认证错误处理中心
    }
}
