package com.example.demo.controller;

import com.example.demo.pojo.Result;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtUtil;
import com.example.demo.utils.Md5Util;
import com.example.demo.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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
                Map<String, Object> claims = new HashMap<>();
                claims.put("id", loginUser.getId());
                claims.put("username", loginUser.getUsername());
                String token = JwtUtil.genToken(claims);
                return Result.success(token);
            } else {
                return Result.error("password error");
            }
        }
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    /**
     * put function update entire user info
     *
     * @param user user
     * @return result
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    /**
     * patch function update some param of user
     *
     * @param avatarUrl avatarUrl
     * @return result
     */
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, Object> pwdMap) {
        //check map
        String oldPwd = (String) pwdMap.get("old_pwd");
        String newPwd = (String) pwdMap.get("new_pwd");
        String rePwd = (String) pwdMap.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("missing parameter of update password");
        }
        //check old password
        Map<String,Object> claim = ThreadLocalUtil.get();
        User loginUser = userService.findByUsername((String) claim.get("username"));
        if(!loginUser.getPassword().equals(Md5Util.getMD5String(oldPwd))){
            return Result.error("old password error");
        }
        if(!newPwd.equals(rePwd)){
            return Result.error("two new password is not matched");
        }
        userService.updatePwd(newPwd);
        return Result.success();

    }
}
