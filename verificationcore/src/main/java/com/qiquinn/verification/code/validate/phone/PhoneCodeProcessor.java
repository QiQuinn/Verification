package com.qiquinn.verification.code.validate.phone;

import com.qiquinn.verification.code.api.PhoneCodeSender;
import com.qiquinn.verification.code.impl.AbstractValidateProcessor;
import com.qiquinn.verification.code.validate.entity.PhoneCode;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import com.qiquinn.verification.properties.login.validatecimge.PhoneCodeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
@Component("phoneCodeProcessor")
public class PhoneCodeProcessor extends AbstractValidateProcessor<PhoneCode>
{
    @Autowired
    private PhoneCodeSender phoneCodeSender;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private SecurityCoreProperties securityCoreProperties;

    @Override
    protected void send(ServletWebRequest request, PhoneCode codeObject) throws Exception
    {
        String phone = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"phone");
        codeObject.setPhoneNumber(phone);
        sessionStrategy.setAttribute(request,SESSION_KEY_PREFIX+"PHONE",codeObject);
        PhoneCodeProperties properties = securityCoreProperties.getValidate().getPhonecode();
        phoneCodeSender.Send(phone,codeObject.getCode(),properties.getSid(),properties.getToken(),properties.getAppId());
    }
}
