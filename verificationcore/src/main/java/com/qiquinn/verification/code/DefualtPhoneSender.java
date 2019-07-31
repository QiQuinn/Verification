package com.qiquinn.verification.code;

import com.qiquinn.verification.code.api.PhoneCodeSender;

/**
 * @Author:QiQuinn
 * @Desicription: 默认手机验证码发送器
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public class DefualtPhoneSender implements PhoneCodeSender
{
    @Override
    public void Send(String phoneNumber, String code)
    {
        System.out.println("手机号： "+phoneNumber+" , 验证码: "+code);
    }
}
