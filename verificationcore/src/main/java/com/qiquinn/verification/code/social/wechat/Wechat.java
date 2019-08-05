package com.qiquinn.verification.code.social.wechat;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/5
 * @Modified By:
 */
public interface Wechat
{
    WechatUserInfo getWechtUserInfo(String openId);
}
