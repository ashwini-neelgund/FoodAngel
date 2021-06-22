package com.foodangel.service.impl;

import com.foodangel.dao.ItemDao;
import com.foodangel.dao.RequestDao;
import com.foodangel.dao.UserDao;
import com.foodangel.model.Item;
import com.foodangel.model.ItemRequested;
import com.foodangel.model.Request;
import com.foodangel.model.User;
import com.foodangel.service.RequestService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("RequestService")
public class RequestServiceImpl implements RequestService {

    private UserDao userDao;
    private RequestDao requestDao;
    private ItemDao itemDao;

    public RequestServiceImpl(UserDao userDao, RequestDao requestDao, ItemDao itemDao) {
        this.userDao = userDao;
        this.requestDao = requestDao;
        this.itemDao = itemDao;
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
        Request request = seeker.getRequests().get(0);
        request.setStatus("active");
        List<ItemRequested> items = request.getItemsRequested();
        List<ItemRequested> itemReq = new ArrayList<>();
        for (ItemRequested item:items) {
            Item itemData = itemDao.getById(item.getId());
            ItemRequested currItem = new ItemRequested();
            currItem.setId(null);
            currItem.setName(itemData.getName());
            currItem.setDescription(itemData.getDescription());
            itemReq.add(currItem);
        }
        request.getItemsRequested().clear();
        request.setItemsRequested(itemReq);
        seeker.getRequests().add(request);
        User persistedSeeker = userDao.save(seeker);;
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

    @Override
    public List<Item> getItems() {
        return itemDao.findAll();
    }
}
