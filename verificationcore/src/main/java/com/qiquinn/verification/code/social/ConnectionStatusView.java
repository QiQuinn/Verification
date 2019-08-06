package com.qiquinn.verification.code.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:QiQuinn
 * @Desicription: 微信绑定返回的界面
 * @Date:Created in 2019/8/5
 * @Modified By:
 */
public class ConnectionStatusView extends AbstractView
{
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception
    {
        Map<String,List<Connection<?>>> connections =(Map<String,List<Connection<?>>>) model.get("connectionMap");
        /* 如果返回过来的数据中有connection表示解绑操作，没有的话就是绑定操作 */
        if(model.get("connection")==null)
        {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<H2>绑定失败</H2>");
        }
        else {
            returnHTML(connections,request,response);
//        returnJson(connections,request,response);
        }

    }

    private void returnHTML(Map<String,List<Connection<?>>> connections,HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse) throws Exception
    {
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        httpServletResponse.getWriter().write("<H2>绑定成功</H2>");
    }

    private void returnJson(Map<String,List<Connection<?>>> connections,HttpServletRequest httpServletRequest,
                            HttpServletResponse httpServletResponse) throws Exception
    {
        Map<String,Boolean> result = new HashMap<>();
        for(String key : connections.keySet())
        {
            result.put(key, CollectionUtils.isNotEmpty(connections.get(key)));
        }
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
