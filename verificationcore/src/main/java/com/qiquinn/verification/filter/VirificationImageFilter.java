package com.qiquinn.verification.filter;

import com.qiquinn.verification.VaildataException;
import com.qiquinn.verification.code.ImageCode;
import com.qiquinn.verification.controller.VirificationImageController;
import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author:QiQuinn
 * @Desicription: 验证码过滤器
 * @Date:Created in 2019/7/30
 * @Modified By:
 */
public class VirificationImageFilter extends OncePerRequestFilter implements InitializingBean{
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    private final String LoginTableUrl = "/authentication/form";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private SecurityCoreProperties securityCoreProperties;
    //不匹配的路径
    private AntPathMatcher pathMatcher = new AntPathMatcher();
    //验证码验证的url集合
    private Set<String> urls = new HashSet<>();

    @Override
    public void afterPropertiesSet() throws ServletException {
        System.out.println("======================= BeanCreate =================");
        String[] configStr = StringUtils.split(
                securityCoreProperties.getValidate().getImagecode().getUrl(),",");
        for(String url : configStr)
        {
            urls.add(url);
        }
        urls.add(LoginTableUrl); //表单登陆请求要来验证验证码
        super.afterPropertiesSet();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls)
        {
            if(pathMatcher.match(url,request.getRequestURI()))
            {
                action = true;
            }
        }
        if(action)
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
        ImageCode codeInSession = (ImageCode)sessionStrategy.getAttribute(request, VirificationImageController.SESSION_KEY);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(),"imagecode");
        if(StringUtils.isBlank(codeInRequest))
        {
            throw new VaildataException("验证码不能为空");
        }
        if(codeInSession == null)
        {
            throw new VaildataException("验证码不存在");
        }
        if(codeInSession.isExpried())
        {
            sessionStrategy.removeAttribute(request,VirificationImageController.SESSION_KEY);
            throw new VaildataException("验证码过期");
        }
        if(!StringUtils.equals(codeInSession.getCode().toLowerCase(),codeInRequest))
        {
            throw new VaildataException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request,VirificationImageController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public String getLoginTableUrl() {
        return LoginTableUrl;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public SecurityCoreProperties getSecurityCoreProperties() {
        return securityCoreProperties;
    }

    public void setSecurityCoreProperties(SecurityCoreProperties securityCoreProperties) {
        this.securityCoreProperties = securityCoreProperties;
    }

    public AntPathMatcher getPathMatcher() {
        return pathMatcher;
    }

    public void setPathMatcher(AntPathMatcher pathMatcher) {
        this.pathMatcher = pathMatcher;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }
}
