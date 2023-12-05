package com.finalproj5003.libarysystem.service;

import com.finalproj5003.libarysystem.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class AdminService
{
    public boolean checkPassword(String userName,String password)
    {
        return userName.equals(Admin.userName)&&password.equals(Admin.password);
    }
}
