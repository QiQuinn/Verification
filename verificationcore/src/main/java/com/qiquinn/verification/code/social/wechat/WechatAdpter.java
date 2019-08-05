package com.qiquinn.verification.code.social.wechat;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/5
 * @Modified By:
 */

public class WechatAdpter implements ApiAdapter<Wechat>
{
    private String openId;
    public WechatAdpter(){}
    public WechatAdpter(String openId){ this.openId = openId;}
    @Override
    public boolean test(Wechat wechat) {
        return true;
    }

    @Override
    public void setConnectionValues(Wechat api, ConnectionValues connectionValues) {
        WechatUserInfo profile = api.getWechtUserInfo(openId);
        connectionValues.setProviderUserId(profile.getOpenid());
        connectionValues.setDisplayName(profile.getNickname());
        connectionValues.setImageUrl(profile.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(Wechat wechat) {
        return null;
    }

    @Override
    public void updateStatus(Wechat wechat, String s) {

    }
}
