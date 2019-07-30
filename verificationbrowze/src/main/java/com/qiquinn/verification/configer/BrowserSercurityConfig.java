package com.qiquinn.verification.configer;

import com.qiquinn.verification.filter.VirificationImageFilter;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/29
 * @Modified By:
 */
@Configuration
public class BrowserSercurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityCoreProperties securityCoreProperties;
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    @Autowired
    private AuthenticationSuccessHandler browzeAuthenticationSuccessHandler;
    @Autowired
    AuthenticationFailureHandler browzeAuthenticationFailedHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        VirificationImageFilter virificationImageFilter = new VirificationImageFilter();
        virificationImageFilter.setAuthenticationFailureHandler(browzeAuthenticationFailedHandler);
        /* 表单登陆验证，对所欲请求添加 */
        http.addFilterBefore(virificationImageFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")  //登陆请求
                .loginProcessingUrl("/authentication/form")
                .successHandler(browzeAuthenticationSuccessHandler) //登陆成功处理器
                .failureHandler(browzeAuthenticationFailedHandler) //认证错误处理中心
                .and()
                .authorizeRequests()
                //可以放行的url
                .antMatchers("/authentication/require"
                        ,"/code/image"
                        , securityCoreProperties.getBrowze().getLoginPage()).permitAll()
                .anyRequest()  //对所有请求都拦截
                .authenticated()
                .and()
                .csrf().disable();
    }
}
