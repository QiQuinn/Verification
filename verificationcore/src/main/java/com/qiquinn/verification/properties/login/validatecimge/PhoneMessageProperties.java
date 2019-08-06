package com.qiquinn.verification.properties.login.validatecimge;

/**
 * @Author:QiQuinn
 * @Desicription: 发送短信基础设置
 * @Date:Created in 2019/8/5
 * @Modified By:
 */
public class PhoneMessageProperties
{
    private String sid;
    private String token;
    private String appId;
    private String type;
    private String content;

    public String getSid()
    {
        return sid;
    }
    public void setSid(String sid)
    {
        this.sid = sid;
    }
    public String getToken()
    {
        return token;
    }
    public void setToken(String token)
    {
        this.token = token;
    }
    public String getAppId()
    {
        return appId;
    }
    public void setAppId(String appId)
    {
        this.appId = appId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}
