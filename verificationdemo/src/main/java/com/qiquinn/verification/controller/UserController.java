package com.qiquinn.verification.controller;

import com.qiquinn.verification.mould.User;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/26
 * @Modified By:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(value = "/{userId:\\d+}")
    public Map<String,Object> findUserById(@PathVariable("userId") Integer userId)
    {
        Map<String,Object> result = new HashMap<>();
        result.put("aaa","qiquinn");
        result.put("ss","faker");
        result.put("userId",userId);
        return result;
    }

    @PostMapping("/user")
    @ResponseBody
    public User createUser(@RequestBody User user)
    {
        System.out.println("request user: "+user);
        return user;
    }

}
