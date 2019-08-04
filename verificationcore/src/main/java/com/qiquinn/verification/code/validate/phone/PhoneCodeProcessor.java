package com.qiquinn.verification.code.validate.phone;

import com.qiquinn.verification.code.api.PhoneCodeSender;
import com.qiquinn.verification.code.impl.AbstractValidateProcessor;
import com.qiquinn.verification.code.validate.entity.PhoneCode;
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
    @Override
    protected void send(ServletWebRequest request, PhoneCode codeObject) throws Exception
    {
        String phone = ServletRequestUtils.getRequiredStringParameter(request.getRequest(),"phone");
        codeObject.setPhoneNumber(phone);
        sessionStrategy.setAttribute(request,SESSION_KEY_PREFIX+"PHONE",codeObject);
        phoneCodeSender.Send(phone,codeObject.getCode());
    }
}
