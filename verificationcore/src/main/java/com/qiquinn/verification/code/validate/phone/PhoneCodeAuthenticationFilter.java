package com.qiquinn.verification.code.validate.phone;

import com.qiquinn.verification.VerificationConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:QiQuinn
 * @Desicription: 手机验证码登陆过滤器
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public class PhoneCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter
{
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
        return request.getParameter(VerificationConstants.DEFUALT_PARAMETER_PHONE_NUMBER);
    }

    protected void setDetails(HttpServletRequest request, PhoneCodeAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }


}
