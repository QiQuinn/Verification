package com.qiquinn.verification.code;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Author:QiQuinn
 * @Desicription: 图形验证码封装
 * @Date:Created in 2019/7/30
 * @Modified By:
 */
public class ImageCode
{
    private BufferedImage image;
    private String code;
    private LocalDateTime expireTime;
    private String url;

    public ImageCode(BufferedImage image,String code,int loseTime)
    {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(loseTime);
    }

    public ImageCode(BufferedImage iamge, String code, LocalDateTime expireTime) {
        this.image = iamge;
        this.code = code;
        this.expireTime = expireTime;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    public boolean isExpried()
    {
        if (expireTime.isBefore(LocalDateTime.now()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
