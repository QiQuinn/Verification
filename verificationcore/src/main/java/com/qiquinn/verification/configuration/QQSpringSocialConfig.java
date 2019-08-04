package com.qiquinn.verification.configuration;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/2
 * @Modified By:
 */
public class QQSpringSocialConfig extends SpringSocialConfigurer
{
    private String processUrl;

    public QQSpringSocialConfig(String processUrl)
    {
        this.processUrl = processUrl;
    }

    @Override
    protected <T> T postProcess(T object)
    {
        SocialAuthenticationFilter filter =(SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(processUrl);
        return (T) filter;
    }
}
