package com.qiquinn.verification.result;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiquinn.verification.properties.login.LoginResultType;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:QiQuinn
 * @Desicription: 登陆成功处理中心
 * @Date:Created in 2019/7/30
 * @Modified By:
 */
@Component("browzeAuthenticationHandler")
public class BrowzeAuthenticationSuccess extends SavedRequestAwareAuthenticationSuccessHandler{
    private Logger logger = LoggerFactory.getLogger(BrowzeAuthenticationSuccess.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityCoreProperties securityCoreProperties;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登陆成功");
        if(LoginResultType.JSON.equals(securityCoreProperties.getBrowze().getLoginResultType()))
        {
            response.setContentType("application/json,charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }
        else
        {
            super.onAuthenticationSuccess(request,response,authentication);
        }

    }
}
