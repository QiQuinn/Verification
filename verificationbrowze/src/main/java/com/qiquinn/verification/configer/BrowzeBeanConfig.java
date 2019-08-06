package com.qiquinn.verification.configer;

import com.qiquinn.verification.result.LoginOutSuccessHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/6
 * @Modified By:
 */
@Configuration
public class BrowzeBeanConfig
{
    @Bean
    @ConditionalOnMissingBean(LoginOutSuccessHandler.class)
    public LoginOutSuccessHandler loginOutSuccessHandler()
    {
        return new LoginOutSuccessHandler();
    }
}
