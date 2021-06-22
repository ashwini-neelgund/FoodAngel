package com.foodangel.service.impl;

import com.foodangel.dao.RequestDao;
import com.foodangel.dao.UserDao;
import com.foodangel.model.Request;
import com.foodangel.model.User;
import com.foodangel.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private RequestDao requestDao;

    public UserServiceImpl(UserDao userDao, RequestDao requestDao) {
        this.userDao = userDao;
        this.requestDao = requestDao;
    }

    @Override
    public User userRegistration(User user) {
        user.setUserType("angel");
        return userDao.save(user);
    }

    @Override
    public List<Request> getAllNewRequestsInArea(String email) {
        User angel = userDao.findByEmailAddress(email);
        return requestDao.findByStatusAndAngelIdAndZipcode(angel.getAddress().get(0).getZipCode());
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
