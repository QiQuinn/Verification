package com.qiquinn.verification.properties.login.validatecimge;

/**
 * @Author:QiQuinn
 * @Desicription: 短信验证码类
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public class PhoneCodeProperties extends PhoneMessageProperties
{
    private Integer count;
    private Integer loseTime;
    private String url;

    public PhoneCodeProperties()
    {
        this.setType("4");  //短信验证码时4
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Integer getLoseTime() {
        return loseTime;
    }
    public void setLoseTime(Integer loseTime) {
        this.loseTime = loseTime;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
}
