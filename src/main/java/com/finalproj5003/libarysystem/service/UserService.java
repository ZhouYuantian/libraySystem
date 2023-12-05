package com.finalproj5003.libarysystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.finalproj5003.libarysystem.dao.mapper.UserDao;
import com.finalproj5003.libarysystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserDao userDao;

    public void addUser(User user)
    {
        userDao.insert(user);
    }

    public void deleteUser(String userName)
    {
        userDao.delete(new QueryWrapper<User>().eq("user_name",userName));
    }

    public void updateUser(String id,User user)
    {
        userDao.update(user,new QueryWrapper<User>().eq("id",id));
    }

    public boolean checkPassword(String userName,String password)
    {
        return userDao.selectCount(new QueryWrapper<User>().eq("user_name",userName)
                .eq("password",password))>0;
    }

    public User getUserByUserName(String userName)
    {
        return userDao.selectOne(new QueryWrapper<User>().eq("user_name",userName));
    }
    public boolean checkExistence(String userName)
    {
        return userDao.selectCount(new QueryWrapper<User>().eq("user_name",userName))>0;
    }

    public int getCountByUserName(String userName)
    {
        return userDao.selectCount(new QueryWrapper<User>().like("user_name",userName));
    }

    public List<User> findAll(String userName)
    {
        return userDao.selectList(new QueryWrapper<User>().like("user_name",userName));
    }
    public List<User> findAll()
    {
        return userDao.selectList(new QueryWrapper<User>());
    }
}
