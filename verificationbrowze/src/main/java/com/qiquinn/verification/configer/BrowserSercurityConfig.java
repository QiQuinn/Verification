package com.qiquinn.verification.configer;

import com.qiquinn.verification.VerificationConstants;
import com.qiquinn.verification.code.validate.image.ImageCodeAuthenticationConfiger;
import com.qiquinn.verification.configuration.BaseAbstractChannelSecurityConfig;
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
public class BrowserSercurityConfig extends BaseAbstractChannelSecurityConfig
{
    @Autowired
    private SecurityCoreProperties securityCoreProperties;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PhoneCodeAuthenticationConfiger phoneCodeAuthenticationConfiger;
    @Autowired
    private ImageCodeAuthenticationConfiger imageCodeAuthenticationConfiger;
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
    protected void configure(HttpSecurity http) throws Exception
    {
        applyPasswordAuthenticationConfig(http);
        /* 表单登陆验证，对所欲请求添加 */
        http.apply(phoneCodeAuthenticationConfiger) //加入短信验证码登陆
                .and()
                .apply(imageCodeAuthenticationConfiger)
                .and() //配置记住我的功能
                    .rememberMe()
                        .tokenRepository(persistentTokenRepository())
                        .tokenValiditySeconds(securityCoreProperties.getBrowze().getRememberMeTime())
                        .userDetailsService(userDetailsService) //用这个登陆
                .and()
                    .authorizeRequests()
                    //可以放行的url
                    .antMatchers(VerificationConstants.LOGIN_REQUERE_PATH
                            ,VerificationConstants.LOGIN_AUTHNTICATION_PHONE
                            ,VerificationConstants.IGNORE_PATH
                            , securityCoreProperties.getBrowze().getLoginPage()).permitAll()
                    .anyRequest()  //对所有请求都拦截
                    .authenticated()
                .and()
                    .csrf().disable(); //关闭浏览器防护
    }
}
