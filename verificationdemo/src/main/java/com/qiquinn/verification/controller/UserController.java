package com.qiquinn.verification.controller;

import com.qiquinn.verification.mould.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/26
 * @Modified By:
 */
@RestController
public class UserController {

    @GetMapping("/user/me")
    public Object getNowUser(@AuthenticationPrincipal UserDetails user)
    {
        return user;
    }
    @Autowired
    private ProviderSignInUtils providerSignInUtil;

    @GetMapping("/user")
    public List<Map<String,Object>> findUser()
    {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> result = new HashMap<>();
        result.put("aaa","qiquinn");
        result.put("ss","faker");
        result.put("userId",1);
        Map<String,Object> result1 = new HashMap<>();
        result1.put("aaa","风清扬");
        result1.put("ss","撒");
        result1.put("userId",2);
        Map<String,Object> result2 = new HashMap<>();
        result2.put("aaa","xqy");
        result2.put("ss","marlin");
        result2.put("userId",3);

        list.add(result);
        list.add(result1);
        list.add(result2);
        return list;
    }

    @GetMapping(value = "/user/{userId:\\d+}")
    public Map<String,Object> findUserById(@PathVariable("userId") Integer userId)
    {
        Map<String,Object> result = new HashMap<>();
        result.put("aaa","qiquinn");
        result.put("ss","faker");
        result.put("userId",userId);
        return result;
    }

    @PostMapping("/user/regist")
    public Map<String,Object> registUser(User user, HttpServletRequest request)
    {
        //不管是注册用户还是绑定用户，都会有一个唯一标识
        String userId = user.getUsername();
        providerSignInUtil.doPostSignUp(userId,new ServletWebRequest(request));
        return null;
    }

    @PostMapping("/user")
    @ResponseBody
    public User createUser(@RequestBody User user)
    {
        System.out.println("request user: "+user);
        return user;
    }

}
