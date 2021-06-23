package com.foodangel.service;

import com.foodangel.model.Request;
import com.foodangel.model.User;

import java.util.List;

public interface UserService {

    User userRegistration(User angel);

    List<Request> getAllNewRequestsInArea(String username);

    List<Request> getAllRequestsAssignedToAngel(String username);

    void addRequestToAngel(Long requestId, String username);

    void removeRequestAssignedTOAngel(Long requestId, String username);

    void updateReqStatusAsComplete(Long requestId);
}
