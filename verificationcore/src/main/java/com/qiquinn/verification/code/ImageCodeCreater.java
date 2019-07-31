package com.qiquinn.verification.code;

import com.qiquinn.verification.ImageCodeUtils;
import com.qiquinn.verification.code.api.ValiadateCodeGenerator;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/30
 * @Modified By:
 */
public class ImageCodeCreater implements ValiadateCodeGenerator
{
    private SecurityCoreProperties securityCoreProperties;
    @Override
    public ImageCode createCode(HttpServletRequest request)
    {
        int imageWidth = ServletRequestUtils.getIntParameter(request,"width",securityCoreProperties.getValidate().getImagecode().getWidth());
        int imageHeight = ServletRequestUtils.getIntParameter(request,"height",securityCoreProperties.getValidate().getImagecode().getHeight());
        int imageLength = ServletRequestUtils.getIntParameter(request,"length",securityCoreProperties.getValidate().getImagecode().getLength());
        int imageLoseTime = securityCoreProperties.getValidate().getImagecode().getExpireIn();
        ImageCodeUtils imageCodeUtils = new ImageCodeUtils(imageWidth,imageHeight,imageLength,request);
        BufferedImage bufferedImage = imageCodeUtils.getImage();
        String imageCodeString = imageCodeUtils.getText();
        ImageCode imageCode = new ImageCode(bufferedImage,imageCodeString,imageLoseTime);
        return imageCode;
    }

    public SecurityCoreProperties getSecurityCoreProperties() {
        return securityCoreProperties;
    }

    public void setSecurityCoreProperties(SecurityCoreProperties securityCoreProperties) {
        this.securityCoreProperties = securityCoreProperties;
    }
}
