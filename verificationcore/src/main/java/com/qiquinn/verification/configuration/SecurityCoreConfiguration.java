package com.qiquinn.verification.configuration;

import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/29
 * @Modified By:
 */
@Configuration
@EnableConfigurationProperties({SecurityCoreProperties.class})
public class SecurityCoreConfiguration
{

}
