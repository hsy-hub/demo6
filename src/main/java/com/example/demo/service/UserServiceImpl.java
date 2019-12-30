package com.example.demo.service;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
@Autowired
    UserMapper userMapper;
    @Override
    public User selectByid(Integer id) {
        return userMapper.selectByid(id);
    }

    @Override
    public int updateUserByid(User user) {
        return userMapper.updateUserByid(user);
    }

    @Override
    public int deleteUserByid(Integer id) {
        return userMapper.deleteUserByid(id);
    }
}
