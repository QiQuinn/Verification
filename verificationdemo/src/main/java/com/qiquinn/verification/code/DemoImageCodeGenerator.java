package com.qiquinn.verification.code;

import com.qiquinn.verification.code.api.ValiadateCodeGenerator;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public class DemoImageCodeGenerator implements ValiadateCodeGenerator
{
    @Override
    public ImageCode createCode(HttpServletRequest request) {
        System.out.println("==========高级的图验证码生成代码====================");
        return null;
    }
}
