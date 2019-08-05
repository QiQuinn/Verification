package com.qiquinn.verification.result;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiquinn.verification.properties.login.LoginResultType;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:QiQuinn
 * @Desicription: 验证失败处理
 * @Date:Created in 2019/7/30
 * @Modified By:
 */
@Component("BrowzeAuthenticationFail")
public class BrowzeAuthenticationFail extends SimpleUrlAuthenticationFailureHandler {
    private Logger log = LoggerFactory.getLogger(BrowzeAuthenticationSuccess.class);
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SecurityCoreProperties securityCoreProperties;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
            , AuthenticationException e) throws IOException, ServletException {
        if(LoginResultType.JSON.equals(securityCoreProperties.getBrowze().getLoginResultType()))
        {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(e.getCause()+" : "+e.getMessage()));
        }
        else
        {
            super.onAuthenticationFailure(request,response,e);
        }

    }
}
