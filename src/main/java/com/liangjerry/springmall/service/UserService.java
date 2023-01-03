package com.liangjerry.springmall.service;

import com.liangjerry.springmall.dto.UserRegisterRequest;
import com.liangjerry.springmall.model.User;

public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest registerRequest);
}
