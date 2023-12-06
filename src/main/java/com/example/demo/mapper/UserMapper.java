package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //select user by username
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    //register user
    @Insert("insert into user(username,password,create_time,update_time)" +
            "values (#{username},#{md5Password},now(),now())")
    void addUser(String username, String md5Password);
}
