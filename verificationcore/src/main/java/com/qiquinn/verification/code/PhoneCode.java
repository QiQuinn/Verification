package com.qiquinn.verification.code;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/31
 * @Modified By:
 */

public class PhoneCode extends BaseCode
{
    private String phoneNumber;
    private String url;

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public PhoneCode(String code, Integer time) {
        super(code, time);
    }
}
