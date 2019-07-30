package com.qiquinn.verification.configuration;

import com.qiquinn.verification.code.ImageCode;
import com.qiquinn.verification.code.ImageCodeCreater;
import com.qiquinn.verification.code.ValiadateCodeGenerator;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/30
 * @Modified By:
 */
@Configuration
public class ValiadateCodeBeanConfig {
    @Autowired
    private SecurityCoreProperties securityCoreProperties;
    //不在ImageCodeCreater在@Compent因为可以加@ConditionalOnMissingBean
    //@ConditionalOnMissingBean可以在容器中找是否存在这个Bean，如果不存在再放入到容器中去
    @Bean
    @ConditionalOnMissingBean(name = "imageCodeCreater")
    public ValiadateCodeGenerator imageCodeCreater()
    {
        ImageCodeCreater imageCodeCreater = new ImageCodeCreater();
        imageCodeCreater.setSecurityCoreProperties(securityCoreProperties);
        return imageCodeCreater;
    }
}
