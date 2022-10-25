package com.example.springbootmall.service.impl;

import com.example.springbootmall.dao.UserDao;
import com.example.springbootmall.dto.UserLoginRequest;
import com.example.springbootmall.dto.UserRegisterRequest;
import com.example.springbootmall.model.User;
import com.example.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private final static Logger log = LoggerFactory.getLogger(UserService.class);

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        // 檢查註冊的 email
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if(user != null) {
            log.warn("該 email {} 已經被註冊了", userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 創建帳號
        return userDao.createUser(userRegisterRequest);
    }

    @Override
    public User login(UserLoginRequest userLoginRequest) {

        User user = userDao.getUserByEmail(userLoginRequest.getEmail());

        if(user == null) {
            log.warn("該 email {} 尚未註冊", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        if(user.getPassword().equals(userLoginRequest.getPassword())) {
            return user;
        } else {
            log.warn("email {} 的密碼不正確", userLoginRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
