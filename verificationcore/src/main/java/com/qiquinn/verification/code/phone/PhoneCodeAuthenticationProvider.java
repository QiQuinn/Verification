package com.qiquinn.verification.code.phone;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public class PhoneCodeAuthenticationProvider implements AuthenticationProvider
{
    private UserDetailsService userDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PhoneCodeAuthenticationToken authenticationToken = (PhoneCodeAuthenticationToken)authentication;

        UserDetails user = userDetailsService.loadUserByUsername(String.valueOf(authenticationToken.getPrincipal()));
        if(user == null)
        {
            throw new InternalAuthenticationServiceException("无法读取用户信息");
        }
        PhoneCodeAuthenticationToken authenticationTokenRole = new PhoneCodeAuthenticationToken(user,user.getAuthorities());
        authenticationTokenRole.setDetails(authenticationToken.getDetails());
        return authenticationTokenRole;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PhoneCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
