package com.example.demo.mapper;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    //select user by username
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    //register user
    @Insert("insert into user(username,password,create_time,update_time)" +
            "values (#{username},#{md5Password},now(),now())")
    void addUser(String username, String md5Password);

    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);
}
