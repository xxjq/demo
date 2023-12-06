package com.example.demo.service.impl;

import com.example.demo.mapper.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        //encryption
        String md5Password = Md5Util.getMD5String(password);
        //register user
        userMapper.addUser(username, md5Password);
    }
}
