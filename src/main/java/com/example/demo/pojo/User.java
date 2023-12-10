package com.example.demo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    /**
     * when get request param user,validate the value is not null
     */
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名

    /**
     * when cast to JSON String,ignore the value
     */
    @JsonIgnore
    private String password;//密码

    /**
     * when get request param user,validate the value is not empty and fit the regexp
     */
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称

    @NotEmpty
    @Email
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
