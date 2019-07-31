package com.qiquinn.verification.code.phone;

import com.qiquinn.verification.VaildataException;
import com.qiquinn.verification.code.BaseCode;
import com.qiquinn.verification.code.phone.PhoneCodeAuthenticationToken;
import com.qiquinn.verification.controller.VirificationImageController;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.Assert;
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
 * @Desicription: 手机验证码登陆过滤器
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public class PhoneCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter
{
    public static final String SPRING_SECURITY_FORM_PHONE_KEY = "PHONE";
    private String userPhone = SPRING_SECURITY_FORM_PHONE_KEY;
    private boolean postOnly = true;

    public PhoneCodeAuthenticationFilter() {
        super(new AntPathRequestMatcher("/authentication/phone", "POST"));
    }

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if(this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        } else {
            String username = this.obtainPhone(request);
            if(username == null) {
                username = "";
            }
            username = username.trim();
            PhoneCodeAuthenticationToken authRequest = new PhoneCodeAuthenticationToken(username);
            this.setDetails(request, authRequest);
            return this.getAuthenticationManager().authenticate(authRequest);
        }
    }


    protected String obtainPhone(HttpServletRequest request) {
        return request.getParameter(this.userPhone);
    }

    protected void setDetails(HttpServletRequest request, PhoneCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setUserPhone(String usernameParameter) {
        Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
        this.userPhone = usernameParameter;
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getUserPhone() {
        return this.userPhone;
    }
}
