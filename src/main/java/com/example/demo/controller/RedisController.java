package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    UserService userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;


    @RequestMapping("/selectByid/{id}")
    @ResponseBody
    public String selectByid(@PathVariable(name = "id") Integer id) {
        User user = userService.selectByid(id);
        String loginName = user.getLoginName();
        ValueOperations<String, String> op = stringRedisTemplate.opsForValue();
        op.set("loginName", loginName);
        return op.get("loginName");
    }

    @RequestMapping("/updateUserByid/{id}/{loginName}")
    @ResponseBody
    public User updateUserByid(@PathVariable(name = "id") Integer id,
                               @PathVariable(name = "loginName") String loginName) {
        User user = new User();
        user.setId(id);
        user.setLoginName(loginName);
        userService.updateUserByid(user);
        ValueOperations op1 = redisTemplate.opsForValue();
        User reuser = userService.selectByid(id);
        op1.set("user"+id,reuser);
        User user2 = (User) op1.get("user"+id);
        return user2;
    }

    @RequestMapping("/deleteUserByid/{id}")
    @ResponseBody
    public User updateUserByid(@PathVariable(name = "id") Integer id) {
       int i = userService.deleteUserByid(id);
        ValueOperations op1 = redisTemplate.opsForValue();
       redisTemplate.delete("user"+id);
        User user2 = (User) op1.get("user"+id);
        return user2;
    }

}