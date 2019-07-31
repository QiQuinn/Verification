package com.qiquinn.verification.properties;

import org.springframework.beans.factory.annotation.Value;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/30
 * @Modified By:
 */
public class ValidateCodeProperties
{
    private ImageCodeProperties imagecode = new ImageCodeProperties();

    private PhoneCodeProperties phonecode = new PhoneCodeProperties();

    public PhoneCodeProperties getPhonecode() {
        return phonecode;
    }

    public void setPhonecode(PhoneCodeProperties phonecode) {
        this.phonecode = phonecode;
    }

    public ImageCodeProperties getImagecode() {
        return imagecode;
    }

    public void setImagecode(ImageCodeProperties imagecode) {
        this.imagecode = imagecode;
    }
}
