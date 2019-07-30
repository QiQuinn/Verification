package com.qiquinn.verification.controller;

import com.qiquinn.verification.properties.SecurityCoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/29
 * @Modified By:
 */
@RestController
public class BrowzeSecurityController
{
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private SecurityCoreProperties securityCoreProperties;
    /**
      * @Author:QiQuinn
      * @Desicription: 身份认证跳转控制器
      * @Date:Created in 2019/7/29 17:11
      * @param request 请求
	  * @param response  返回
      *@return java.lang.String
      * @Modified By:
      */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public String requestAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        System.out.println("authentication");
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if(savedRequest!=null)
        {
            String target = savedRequest.getRedirectUrl();
            System.out.println("引发跳转的URL: "+target);
            if(StringUtils.endsWithIgnoreCase(target,".html"))
            {
                System.out.println(".html: "+ securityCoreProperties.getBrowze().getLoginPage());
                redirectStrategy.sendRedirect(request,response, securityCoreProperties.getBrowze().getLoginPage());
            }
        }
        return "访问的服务需要身份验证，请引导用户到登录页";
    }
}
