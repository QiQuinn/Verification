package com.qiquinn.verification;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/30
 * @Modified By:
 */

public class VaildataException extends AuthenticationException {
    private static final long serialVersionUID = -1231234124L;

    public VaildataException (String msg)
    {
        super(msg);
    }
}
