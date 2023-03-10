package com.liangjerry.springmall.service.impl;

import com.liangjerry.springmall.dao.UserDao;
import com.liangjerry.springmall.dto.UserLoginRequest;
import com.liangjerry.springmall.dto.UserRegisterRequest;
import com.liangjerry.springmall.model.User;
import com.liangjerry.springmall.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest registerRequest) {
        //檢查註冊的 email
        User user = userDao.getUserByEmail(registerRequest.getEmail());

        if(user != null){
            log.warn("該 email {} 已經被註冊", registerRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用 MD5 生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(registerRequest.getPassword().getBytes());
        registerRequest.setPassword(hashedPassword);
        //創建帳號
        return userDao.createUser(registerRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {
        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        //檢查註冊的 user 是否存在
        if(user == null){
            log.warn("該 email {} 尚未註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用 MD5 生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequest.getPassword().getBytes());

        //比對密碼
        if(user.getPassword().equals(hashedPassword)){
            return user;
        } else {
            log.warn("email {} 的密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
