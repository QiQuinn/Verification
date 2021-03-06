package com.qiquinn.verification.properties.login.social;

import com.qiquinn.verification.VerificationConstants;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/1
 * @Modified By:
 */

public class QQProperties extends SocialProperties
{
    private String providerId = VerificationConstants.LOGIN_SOCIAL_QQ_PROVIDER_ID;

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
