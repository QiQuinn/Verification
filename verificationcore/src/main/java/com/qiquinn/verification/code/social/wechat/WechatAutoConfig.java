package com.qiquinn.verification.code.social.wechat;

import com.qiquinn.verification.properties.SecurityCoreProperties;
import com.qiquinn.verification.properties.login.social.WechatProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/5
 * @Modified By:
 */
@Configuration
@ConditionalOnProperty(prefix = "qiquinn.security.social.wechat",name = "app-id")
public class WechatAutoConfig extends SocialAutoConfigurerAdapter
{
    @Autowired
    private SecurityCoreProperties securityCoreProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        WechatProperties weixinConfig = securityCoreProperties.getSocial().getWechat();
        return new WechatConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(),
                weixinConfig.getAppSecret());
    }

}
