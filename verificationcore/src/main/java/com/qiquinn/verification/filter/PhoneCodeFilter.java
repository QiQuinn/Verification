package com.qiquinn.verification.filter;

import com.qiquinn.verification.VaildataException;
import com.qiquinn.verification.code.validate.entity.PhoneCode;
import com.qiquinn.verification.code.impl.AbstractValidateProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public class PhoneCodeFilter extends OncePerRequestFilter
{
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    private final String url = "/authentication/phone";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(url.equals(request.getRequestURI()))
        {
            try
            {
                validata(new ServletWebRequest(request));
            }
            catch (VaildataException ex)
            {
                authenticationFailureHandler.onAuthenticationFailure(request,response,ex);
                return;
            }
        }
        filterChain.doFilter(request,response);

    }

    private void validata(ServletWebRequest request) throws ServletRequestBindingException
    {
        PhoneCode codeInSession = (PhoneCode)sessionStrategy.getAttribute(request, AbstractValidateProcessor.SESSION_KEY_PREFIX+"phone".toUpperCase());
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"phoneCode");
        if(StringUtils.isBlank(codeInRequest))
        {
            throw new VaildataException("短信验证码不能为空");
        }
        if(codeInSession == null)
        {
            throw new VaildataException("短信验证码不存在");
        }
        if(codeInSession.isExpried())
        {
            sessionStrategy.removeAttribute(request,AbstractValidateProcessor.SESSION_KEY_PREFIX+"phone".toUpperCase());
            throw new VaildataException("短信验证码过期");
        }
        if(!StringUtils.equals(codeInSession.getCode().toLowerCase(),codeInRequest))
        {
            throw new VaildataException("短信验证码不匹配");
        }
        request.getRequest().setAttribute("username",codeInSession.getPhoneNumber());
        sessionStrategy.removeAttribute(request,AbstractValidateProcessor.SESSION_KEY_PREFIX+"phone".toUpperCase());
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public String getUrl() {
        return url;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }
}
