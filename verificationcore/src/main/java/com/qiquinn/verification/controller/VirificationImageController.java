package com.qiquinn.verification.controller;

import com.qiquinn.verification.ImageCodeUtils;
import com.qiquinn.verification.code.ImageCode;
import com.qiquinn.verification.code.ImageCodeCreater;
import com.qiquinn.verification.code.ValiadateCodeGenerator;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/30
 * @Modified By:
 */
@RestController
public class VirificationImageController {

    private Logger log = LoggerFactory.getLogger(VirificationImageController.class);
    public static final String SESSION_KEY = "SESSION_KEY";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private ValiadateCodeGenerator imageCodeCreater;

    @GetMapping("/code/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response)
    {
        ImageCode imageCode = imageCodeCreater.createImageCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        try
        {
            ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
        }
        catch (IOException ex)
        {
            log.error("写图片认证码错误： "+ex);
        }
    }

}
