package com.qiquinn.verification.controller;

import com.qiquinn.verification.code.BaseCode;
import com.qiquinn.verification.code.DefualtPhoneSender;
import com.qiquinn.verification.code.ImageCode;
import com.qiquinn.verification.code.api.PhoneCodeSender;
import com.qiquinn.verification.code.api.ValiadateCodeGenerator;
import com.qiquinn.verification.code.api.ValidateCodeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/30
 * @Modified By:
 */
@RestController
public class VirificationImageController {

    private Logger log = LoggerFactory.getLogger(VirificationImageController.class);
    public static final String SESSION_IMAGECODE_KEY = "IAMGECODE";
    public static final String SESSION_PHONECODE_KEY = "PHONECODE";
    @Autowired
    private Map<String,ValidateCodeProcessor> validateCodeProcessors;

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response,@PathVariable String type) {
        try {
            ValidateCodeProcessor calidate = validateCodeProcessors.get(type + "CodeProcessor");
            calidate.createCode(new ServletWebRequest(request, response));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("综合方法失败: " + ex.toString());
        }

    }
}
