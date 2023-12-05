package com.finalproj5003.libarysystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String userName;
    private String password;

    public User(String userName, String password)
    {
        setUserName(userName);
        setPassword(password);
    }
}
