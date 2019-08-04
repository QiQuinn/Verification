package com.qiquinn.verification.code.social.qq;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/1
 * @Modified By:
 */

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    /**
  * @Author:QiQuinn
  * @Desicription:
  * @Date:Created in 2019/8/1 15:37
  * @param providerId 运营商的唯一标识
  *@return
  * @Modified By:
  */
    public QQConnectionFactory(String providerId, String appId,String appSecrety)
    {
        super(providerId, new QQServiceProvider(appId,appSecrety), new QQAdapter());
    }
}
