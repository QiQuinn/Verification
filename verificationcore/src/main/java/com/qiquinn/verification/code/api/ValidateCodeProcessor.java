package com.qiquinn.verification.code.api;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Author:QiQuinn
 * @Desicription: 验证码生成接口
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public interface ValidateCodeProcessor {

    String SESSION_KEY_PREFIX="SESSION_KEY_VALIDATECODE_";
    /**
      * @Author:QiQuinn
      * @Desicription: 创建校验码
      * @Date:Created in 2019/7/31 14:27
      * @param request
      *@return void
      * @Modified By:
      */
    void createCode(ServletWebRequest request) throws Exception;
}
