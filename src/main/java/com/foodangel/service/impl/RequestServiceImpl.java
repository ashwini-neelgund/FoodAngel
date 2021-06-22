package com.foodangel.service.impl;

import com.foodangel.dao.RequestDao;
import com.foodangel.dao.UserDao;
import com.foodangel.model.Request;
import com.foodangel.model.User;
import com.foodangel.service.RequestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("RequestService")
public class RequestServiceImpl implements RequestService {

    private UserDao userDao;
    private RequestDao requestDao;

    public RequestServiceImpl(UserDao userDao, RequestDao requestDao) {
        this.userDao = userDao;
        this.requestDao = requestDao;
    }

    @Override
    public Request getRequest(Long id, int pin) {
        Request request = requestDao.getById(id);
        if(request.getPin()==pin)
            return request;
        else
            return null;
    }

    @Override
    public Request addRequest(User seeker) {
        seeker.setUserType("seeker");
        User persistedSeeker = userDao.save(seeker);
        return persistedSeeker.getRequests().get(0);
    }

    @Override
    public Request updateRequest(Request request) {
        return requestDao.save(request);
    }

    @Override
    public void removeRequest(Long requestId) {
        requestDao.delete(requestDao.getById(requestId));
    }

    @Override
    public boolean checkForAngel(int zipcode) {
        List<User> angels = userDao.findAllByZipCode(zipcode);
        return !angels.isEmpty();
    }
}
