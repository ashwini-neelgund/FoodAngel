package com.foodangel.service;

import com.foodangel.model.Request;
import com.foodangel.model.User;

import java.util.List;

public interface UserService {
    User userRegistration(User angel);

    List<Request> getAllNewRequestsInArea(String username);

    List<Request> getAllRequestsAssignedToAngel(String username);

    List<Request> addRequestToAngel(Long requestId, Long angelId);

    List<Request> removeRequestAssignedTOAngel(Long requestId, Long angelId);

    void updateReqStatusAsComplete(Long requestId);
}
