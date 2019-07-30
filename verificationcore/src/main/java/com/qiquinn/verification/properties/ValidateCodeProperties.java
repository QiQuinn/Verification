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

    public ImageCodeProperties getImagecode() {
        return imagecode;
    }

    public void setImagecode(ImageCodeProperties imagecode) {
        this.imagecode = imagecode;
    }
}
