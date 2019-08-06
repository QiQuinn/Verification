package com.qiquinn.verification.result;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiquinn.verification.VerificationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/6
 * @Modified By:
 */
public class LoginOutSuccessHandler implements LogoutSuccessHandler
{
    private Logger logger = LoggerFactory.getLogger(LoginOutSuccessHandler.class);
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(VerificationConstants.JSON_HEARDER);
        response.getWriter().write(objectMapper.writeValueAsString("退出成功！"));
    }
}
