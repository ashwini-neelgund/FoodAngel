package com.foodangel.service.impl;

import com.foodangel.dao.RequestDao;
import com.foodangel.dao.UserDao;
import com.foodangel.model.Request;
import com.foodangel.model.User;
import com.foodangel.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RequestDao requestDao;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RequestDao requestDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.requestDao = requestDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User userRegistration(User user) {
        user.setUserType("angel");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public List<Request> getAllNewRequestsInArea(String email) {
        User angel = userDao.findByEmailAddress(email);
        return requestDao.findByStatusAndAngelIdAndZipcode("seeker", angel.getAddress().get(0).getZipCode(), "active");
    }

    @Override
    public List<Request> getAllRequestsAssignedToAngel(String email) {
        User angel = userDao.findByEmailAddress(email);
        return angel.getRequestsAssignedToAngel();
    }

    @Override
    public List<Request> addRequestToAngel(Long requestId, Long angelId) {
        User angel = userDao.getById(angelId);
        List<Request> requestsAssignedToAngel = angel.getRequestsAssignedToAngel();
        requestsAssignedToAngel.add(requestDao.getById(requestId));
        angel.setRequestsAssignedToAngel(requestsAssignedToAngel);
        return userDao.save(angel).getRequestsAssignedToAngel();
    }

    @Override
    public List<Request> removeRequestAssignedTOAngel(Long requestId, Long angelId) {
        User angel = userDao.getById(angelId);
        List<Request> requestsAssignedToAngel = angel.getRequestsAssignedToAngel();
        requestsAssignedToAngel.remove(requestDao.getById(requestId));
        angel.setRequestsAssignedToAngel(requestsAssignedToAngel);
        return userDao.save(angel).getRequestsAssignedToAngel();
    }

    @Override
    public void updateReqStatusAsComplete(Long requestId) {
        Request request = requestDao.getById(requestId);
        request.setStatus("complete");
        requestDao.save(request);
    }
}
