package com.qiquinn.verification.configuration;

import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/2
 * @Modified By:
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter
{
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SecurityCoreProperties securityCoreProperties;

    @Bean
    public SpringSocialConfigurer springSocialConfigurer()
    {
        String filterProcessiongUrl = securityCoreProperties.getSocial().getFilterProcessesUrl();
        QQSpringSocialConfig springSocialConfig = new QQSpringSocialConfig(filterProcessiongUrl);
        return springSocialConfig;
    }

    /**
     * 业务系统用户和服务提供商用户对应关系,保存在表UserConnection
     * JdbcUsersConnectionRepository.sql 中有建表语句
     *      userId 业务系统Id
     *      providerId 服务提供商的Id
     *      providerUserId  同openId
     * Encryptors  加密策略 这里不加密
     * @param connectionFactoryLocator
     * @return
     */
    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbcUsersConnectionRepository=new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        //设定表UserConnection的前缀 表名不可以改变
        jdbcUsersConnectionRepository.setTablePrefix("qiquinn_");
        return  jdbcUsersConnectionRepository;
    }
    /**
     * 从认证中获取用户信息
     * @return
     */
    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

}
