package com.foodangel.service.impl;

import com.foodangel.dao.UserDao;
import com.foodangel.model.User;
import com.foodangel.service.UserService;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User userRegistration(User user) {
        return userDao.save(user);
    }
}
