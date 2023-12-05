package com.finalproj5003.libarysystem.utils;

import com.finalproj5003.libarysystem.entity.User;
import com.finalproj5003.libarysystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserUtil {
    @Autowired
    private UserService userService;

    private static UserService suserService;

    public static User currentUser;

    @PostConstruct
    public void init()
    {
        suserService=userService;
    }

    public static void loginUser(String userName)
    {
        currentUser=suserService.getUserByUserName(userName);
    }
}
