package com.foodangel.service;

import com.foodangel.model.Item;
import com.foodangel.model.Request;
import com.foodangel.model.User;

import java.util.List;

public interface RequestService {
    Request getRequest(Long id, int pin);

    Request addRequest(User user);

    Request updateRequest(Request request);

    void removeRequest(Long requestId);

    boolean checkForAngel(int zipcode);

    List<Item> getItems();
}
