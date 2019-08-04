package com.qiquinn.verification.configuration;

import com.qiquinn.verification.code.social.qq.QQConnectionFactory;
import com.qiquinn.verification.properties.QQProperties;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/2
 * @Modified By:
 */
@Configuration
@ConditionalOnProperty(prefix = "qiquinn.security.social.qq",name = "app-id")
public class QQAuthoConfig extends SocialAutoConfigurerAdapter
{
    @Autowired
    private SecurityCoreProperties securityCoreProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory()
    {
        QQProperties qqProperties = securityCoreProperties.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProviderId(),qqProperties.getAppId(),qqProperties.getAppSecret());
    }
}
