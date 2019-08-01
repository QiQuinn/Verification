package com.qiquinn.verification.code.validate.image;

import com.qiquinn.verification.filter.VirificationImageFilter;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Desicription:
 * @Date:Created in 2019/8/1
 * @Modified By:
 */
@Component
public class ImageCodeAuthenticationConfiger extends SecurityConfigurerAdapter<DefaultSecurityFilterChain,HttpSecurity>
{
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationSuccessHandler browzeAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler browzeAuthenticationFailedHandler;
    @Autowired
    private SecurityCoreProperties securityCoreProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //图片验证码控制器配置
        VirificationImageFilter virificationImageFilter = new VirificationImageFilter();
        virificationImageFilter.setAuthenticationFailureHandler(browzeAuthenticationFailedHandler);
        virificationImageFilter.setSecurityCoreProperties(securityCoreProperties);
        virificationImageFilter.afterPropertiesSet();
        http.addFilterBefore(virificationImageFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
