package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public User selectByid(Integer id);

    public int updateUserByid(User user);

    public int deleteUserByid(Integer id);
}
