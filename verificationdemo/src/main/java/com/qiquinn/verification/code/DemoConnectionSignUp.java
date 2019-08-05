package com.qiquinn.verification.code;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/8/5
 * @Modified By:
 */
@Component("connectionSignUp")
public class DemoConnectionSignUp implements ConnectionSignUp {
    @Override
    public String execute(Connection<?> connection)
    {
        //根据社交用户信息默认创建用户，并返回唯一标识
        return connection.getDisplayName();
    }
}
