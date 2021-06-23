package com.foodangel.controller;

import com.foodangel.model.Request;
import com.foodangel.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/angel")
@CrossOrigin
@Log
public class AngelController {
    private UserService userService;

    @Autowired
    public AngelController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new/requests/{username}")
    public List<Request> getAllNewRequestsInArea(@PathVariable("username") String username) {
        return userService.getAllNewRequestsInArea(username);
    }

    @GetMapping("/requests/{username}")
    public List<Request> getAllRequestsAssignedToAngel(@PathVariable("username") String username) {
        return userService.getAllRequestsAssignedToAngel(username);
    }

    @GetMapping("/add/request/{requestId}/{username}")
    public List<Request> addRequestToAngel(@PathVariable("requestId") Long requestId, @PathVariable("username") String username) {
        userService.addRequestToAngel(requestId, username);
        return userService.getAllRequestsAssignedToAngel(username);
    }

    @GetMapping("/remove/request/{requestId}/{username}")
    public List<Request> removeRequestAssignedTOAngel(@PathVariable("requestId") Long requestId, @PathVariable("username") String username) {
        userService.removeRequestAssignedTOAngel(requestId, username);
        return userService.getAllRequestsAssignedToAngel(username);
    }

    @GetMapping("/request/status/{requestId}")
    public void updateReqStatusAsComplete(@PathVariable("requestId") Long requestId) {
        userService.updateReqStatusAsComplete(requestId);
    }
}
