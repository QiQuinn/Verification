package com.qiquinn.verification.code.api;

import com.qiquinn.verification.code.BaseCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:QiQuinn
 * @Desicription: 效验码生成器
 * @Date:Created in 2019/7/30
 * @Modified By:
 */
public interface ValiadateCodeGenerator
{
    BaseCode createCode(HttpServletRequest request);
}
