package com.qiquinn.verification.code;

import com.qiquinn.verification.VerificationConstants;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/6
 * @Modified By:
 */
public class SocialSession implements SessionInformationExpiredStrategy
{
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        event.getResponse().setContentType(VerificationConstants.JSON_HEARDER);
        event.getResponse().getWriter().write("并发登陆！");
    }
}
