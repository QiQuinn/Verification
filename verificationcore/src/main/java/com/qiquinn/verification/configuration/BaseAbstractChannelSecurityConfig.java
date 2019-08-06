package com.qiquinn.verification.configuration;

import com.qiquinn.verification.VerificationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @Author:QiQuinn
 * @Desicription: 基本登陆请求配置
 * @Date:Created in 2019/8/1
 * @Modified By:
 */
public class BaseAbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    /**
      * @Author:QiQuinn
      * @Desicription: 登陆配置
      * @Date:Created in 2019/8/6 10:58
      * @param httpSecurity
      *@return void
      * @Modified By:
      */
    protected void applyPasswordAuthenticationConfig(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity.formLogin()
                .loginPage(VerificationConstants.LOGIN_REQUERE_PATH)  //配置当请求需要身份验证时跳转URL
                .loginProcessingUrl(VerificationConstants.LOGIN_PROCESSION_URL) //登陆表单请求
                .successHandler(authenticationSuccessHandler)      //登陆成功处理器
                .failureHandler(authenticationFailureHandler)     //认证错误处理中心
                .and()
                .logout()
                .logoutUrl("/logout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(logoutSuccessHandler);
    }
}
