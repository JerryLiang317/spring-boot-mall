package com.liangjerry.springmall.dao;

import com.liangjerry.springmall.dto.UserRegisterRequest;
import com.liangjerry.springmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
