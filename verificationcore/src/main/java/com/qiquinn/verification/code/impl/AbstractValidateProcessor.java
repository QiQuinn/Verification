package com.qiquinn.verification.code.impl;

import com.qiquinn.verification.code.api.ValiadateCodeCreater;
import com.qiquinn.verification.code.api.ValidateCodeProcessor;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

/**
 * @Author:QiQuinn
 * @Desicription:  验证码生成抽象类(有业务逻辑)
 * @Date:Created in 2019/7/31
 * @Modified By:
 */
public abstract class AbstractValidateProcessor<T> implements ValidateCodeProcessor
{
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    //依赖搜索
    @Autowired
    private Map<String,ValiadateCodeCreater> valiaDateGeneratorMap;

    @Override
    public void createCode(ServletWebRequest request) throws Exception {
        T validateCode = generate(request);
        saveCode(request,validateCode);
        send(request,validateCode);
    }

    private void saveCode(ServletWebRequest request, T codeObject)
    {
        sessionStrategy.setAttribute(request,SESSION_KEY_PREFIX+getProcessorType(request).toUpperCase(),codeObject);
    }

    private T generate(ServletWebRequest request)
    {
        String type = getProcessorType(request);
        ValiadateCodeCreater valiadateCodeGenerator = valiaDateGeneratorMap.get(type+"CodeCreater");
        return (T) valiadateCodeGenerator.createCode(request.getRequest());
    }

    private String getProcessorType(ServletWebRequest request)
    {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    }

    protected abstract void send(ServletWebRequest request, T codeObject)throws Exception;
}
