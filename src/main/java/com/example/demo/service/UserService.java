package com.example.demo.service;

import com.example.demo.pojo.User;

public interface UserService {

    //select user by username
    User findByUsername(String username);

    //register user
    void register(String username, String password);

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
