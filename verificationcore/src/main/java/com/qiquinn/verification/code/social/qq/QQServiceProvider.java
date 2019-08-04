package com.qiquinn.verification.code.social.qq;

import com.qiquinn.verification.VerificationConstants;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/1
 * @Modified By:
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    public QQServiceProvider(String appId,String appSecrety) {
        super(new OAuth2Template(appId,appSecrety, VerificationConstants.LOGIN_QQ_AUTHORIZE,VerificationConstants.LOGIN_QQ_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken)
    {
        return new QQImpl(accessToken,appId);
    }
}
