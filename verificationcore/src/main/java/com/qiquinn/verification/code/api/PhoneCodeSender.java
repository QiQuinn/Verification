package com.qiquinn.verification.code.api;

/**
 * @Author:QiQuinn
 * @Desicription: 短信发送接口
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public interface PhoneCodeSender {
    void Send(String phoneNumber, String code,String sid,String token,String appId);
}
