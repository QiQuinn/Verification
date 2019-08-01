package com.qiquinn.verification.configuration;


import com.qiquinn.verification.code.validate.phone.PhoneCodeAuthenticationFilter;
import com.qiquinn.verification.code.validate.phone.PhoneCodeAuthenticationProvider;
import com.qiquinn.verification.filter.PhoneCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @Author:QiQuinn
 * @Desicription: 配置手机登陆验证的验证流程
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
@Component
public class PhoneCodeAuthenticationConfiger extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity>
{
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        PhoneCodeAuthenticationFilter phoneCodeAuthenticationFilter = new PhoneCodeAuthenticationFilter();
        phoneCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        phoneCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        phoneCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        //手机验证码filter控制器
        PhoneCodeFilter phoneCodeFilter = new PhoneCodeFilter();
        phoneCodeFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        phoneCodeFilter.afterPropertiesSet();

        PhoneCodeAuthenticationProvider provider = new PhoneCodeAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        http.authenticationProvider(provider)
                .addFilterBefore(phoneCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(phoneCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
