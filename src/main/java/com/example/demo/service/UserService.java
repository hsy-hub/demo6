package com.example.demo.service;

import com.example.demo.pojo.User;

public interface UserService {
    public User selectByid(Integer id);
    public int updateUserByid(User user);

    public int deleteUserByid(Integer id);
}
