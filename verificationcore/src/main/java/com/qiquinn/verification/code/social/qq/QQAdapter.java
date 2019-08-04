package com.qiquinn.verification.code.social.qq;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/1
 * @Modified By:
 */

public class QQAdapter implements ApiAdapter<QQ> {

    //判断QQ接口是否联通
    @Override
    public boolean test(QQ qq) {
        return true;
    }
    //第三方数据填充到spring security social连接类中
    @Override
    public void setConnectionValues(QQ qq, ConnectionValues connectionValues) {
        QQUserInfo qqUserInfo = qq.getQQUserInfo();

        connectionValues.setDisplayName(qqUserInfo.getNickname());
        connectionValues.setImageUrl(qqUserInfo.getFigureurl_1());
        connectionValues.setProfileUrl(null);
        connectionValues.setProviderUserId(qqUserInfo.getOpenId());
    }
    //绑定解绑
    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }
    //某些网站可用 时间线或者个人主页
    @Override
    public void updateStatus(QQ qq, String s) {
        //do-noting
    }
}
