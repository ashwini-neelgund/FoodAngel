package com.foodangel.service.impl;

import com.foodangel.dao.RequestDao;
import com.foodangel.dao.UserDao;
import com.foodangel.model.Request;
import com.foodangel.model.User;
import com.foodangel.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RequestDao requestDao;
    private PasswordEncoder encoder;

    public UserServiceImpl(UserDao userDao, RequestDao requestDao,PasswordEncoder encoder) {
        this.userDao = userDao;
        this.requestDao = requestDao;
        this.encoder = encoder;
    }

    @Override
    public User userRegistration(User user) {
        user.setUserType("angel");
        user.setPassword(encoder.encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public List<Request> getAllNewRequestsInArea(String email) {
        User angel = userDao.findAllByEmailAddress(email);
        return requestDao.findByStatusAndAngelIdAndZipcode(angel.getAddress().get(0).getZipCode());
    }

    @Override
    public List<Request> getAllRequestsAssignedToAngel(String email) {
        return requestDao.findByEmailAddressAndRequestStatus(email);
    }

    @Override
    public void addRequestToAngel(Long requestId, String email) {
        User angel = userDao.findAllByEmailAddress(email);
        List<Request> requestsAssignedToAngel = angel.getRequestsAssignedToAngel();
        Request request = requestDao.getById(requestId);
        request.setStatus("assigned");
        requestsAssignedToAngel.add(request);
        angel.setRequestsAssignedToAngel(requestsAssignedToAngel);
        userDao.save(angel);
    }

    @Override
    public void removeRequestAssignedTOAngel(Long requestId, String email) {
        User angel = userDao.findAllByEmailAddress(email);
        List<Request> requestsAssignedToAngel = angel.getRequestsAssignedToAngel();
        Request request = requestDao.getById(requestId);
        request.setStatus("active");
        requestsAssignedToAngel.remove(request);
        angel.setRequestsAssignedToAngel(requestsAssignedToAngel);
        userDao.save(angel);
    }

    @Override
    public void updateReqStatusAsComplete(Long requestId) {
        Request request = requestDao.getById(requestId);
        request.setStatus("complete");
        requestDao.save(request);
    }
}
