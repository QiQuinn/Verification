package com.qiquinn.verification.code.validate.entity;

import com.qiquinn.verification.code.validate.entity.BaseCode;

import java.awt.image.BufferedImage;

/**
 * @Author:QiQuinn
 * @Desicription: 图形验证码封装
 * @Date:Created in 2019/7/30
 * @Modified By:
 */
public class ImageCode extends BaseCode
{
    private BufferedImage image;

    private String url;

    public ImageCode(BufferedImage image,String code,int loseTime)
    {
        super(code,loseTime);
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}
