package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //select user
        User registedUser = userService.findByUsername(username);
        if (registedUser == null) {
            //useless
            userService.register(username, password);
            return Result.success();
        } else {
            //used
            return Result.error("The username is used,please submit another");
        }
    }

    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //select user
        User loginUser = userService.findByUsername(username);
        //Does the user exist
        if (loginUser == null) {
            //not exist
            return Result.error("username error");
        } else {
            //exist and check password
            if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
                //login
                Map<String,Object> claims = new HashMap<>();
                claims.put("id",loginUser.getId());
                claims.put("username",loginUser.getUsername());
                String token = JwtUtil.genToken(claims);
                return Result.success(token);
            } else {
                return Result.error("password error");
            }
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token){
        Map<String,Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }
}
