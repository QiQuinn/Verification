package com.qiquinn.verification.code.validate.phone;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public class PhoneCodeAuthenticationToken extends AbstractAuthenticationToken
{
    private static final long serialVersionUID = 420L;
    private final Object principal;

    public PhoneCodeAuthenticationToken(Object phone)
    {
        super((Collection)null);
        this.principal = phone;
        this.setAuthenticated(false);
    }
    public PhoneCodeAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.principal;
    }
    public Object getPrincipal() {
        return this.principal;
    }
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if(isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }
}
