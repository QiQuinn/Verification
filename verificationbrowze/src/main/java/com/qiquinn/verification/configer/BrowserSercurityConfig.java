package com.qiquinn.verification.configer;

import com.qiquinn.verification.code.phone.PhoneCodeAuthenticationFilter;
import com.qiquinn.verification.configuration.PhoneCodeAuthenticationConfiger;
import com.qiquinn.verification.filter.PhoneCodeFilter;
import com.qiquinn.verification.filter.VirificationImageFilter;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

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
    @Autowired
    private AuthenticationSuccessHandler browzeAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler browzeAuthenticationFailedHandler;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PhoneCodeAuthenticationConfiger phoneCodeAuthenticationConfiger;

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository()
    {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        tokenRepository.setCreateTableOnStartup(false); //数据库自动创建token表
        return tokenRepository;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //图片验证码控制器配置
        VirificationImageFilter virificationImageFilter = new VirificationImageFilter();
        virificationImageFilter.setAuthenticationFailureHandler(browzeAuthenticationFailedHandler);
        virificationImageFilter.setSecurityCoreProperties(securityCoreProperties);
        virificationImageFilter.afterPropertiesSet();
        //手机验证码filter控制器
        PhoneCodeFilter phoneCodeFilter = new PhoneCodeFilter();
        phoneCodeFilter.setAuthenticationFailureHandler(browzeAuthenticationFailedHandler);
        phoneCodeFilter.afterPropertiesSet();
        /* 表单登陆验证，对所欲请求添加 */
        http.addFilterBefore(phoneCodeFilter,UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(virificationImageFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                    .loginPage("/authentication/require")  //登陆请求
                    .loginProcessingUrl("/authentication/form")
                    .successHandler(browzeAuthenticationSuccessHandler) //登陆成功处理器
                    .failureHandler(browzeAuthenticationFailedHandler) //认证错误处理中心
                .and() //配置记住我的功能
                    .rememberMe()
                        .tokenRepository(persistentTokenRepository())
                        .tokenValiditySeconds(securityCoreProperties.getBrowze().getRememberMeTime())
                        .userDetailsService(userDetailsService) //用这个登陆
                .and()
                    .authorizeRequests()
                    //可以放行的url
                    .antMatchers("/authentication/require"
                            ,"/authentication/phone"
                            ,"/code/*"
                            , securityCoreProperties.getBrowze().getLoginPage()).permitAll()
                    .anyRequest()  //对所有请求都拦截
                    .authenticated()
                .and()
                    .csrf().disable()
        .apply(phoneCodeAuthenticationConfiger); //关闭浏览器防护
    }
}
