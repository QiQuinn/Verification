package com.qiquinn.verification.properties;

/**
 * @Author:QiQuinn
 * @Desicription: 图形验证码配置文件
 * @Date:Created in 2019/7/30
 * @Modified By:
 */
public class ImageCodeProperties {
    private Integer width =90;
    private Integer height =35 ;
    private Integer length = 4;
    private Integer expireIn = 60;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getExpireIn() {
        return expireIn;
    }

    public void setExpireIn(Integer expireIn) {
        this.expireIn = expireIn;
    }
}
