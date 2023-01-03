package com.liangjerry.springmall.service.impl;

import com.liangjerry.springmall.dao.UserDao;
import com.liangjerry.springmall.dto.UserRegisterRequest;
import com.liangjerry.springmall.model.User;
import com.liangjerry.springmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest registerRequest) {
        return userDao.createUser(registerRequest);
    }
}
