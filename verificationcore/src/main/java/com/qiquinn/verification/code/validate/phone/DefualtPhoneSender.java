package com.qiquinn.verification.code.validate.phone;

import com.qiquinn.verification.code.api.PhoneCodeSender;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import com.qiquinn.verification.properties.login.validatecimge.PhoneCodeProperties;
import com.qiquinn.verification.utils.http.JsonReqClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author:QiQuinn
 * @Desicription: 默认手机验证码发送器
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public class DefualtPhoneSender implements PhoneCodeSender
{
    private JsonReqClient jsonReqClient = new JsonReqClient();

    @Override
    public void Send(String phoneNumber, String code,String sid,String token,String appId)
    {
        try {

            String result=jsonReqClient.sendSms(sid,token,appId,"491335", code, phoneNumber, "123123");
            System.out.println("Response content is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
