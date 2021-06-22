package com.foodangel.controller;

import com.foodangel.model.Request;
import com.foodangel.model.User;
import com.foodangel.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;

@RestController
@RequestMapping("/angel")
@Log
public class AngelController {

    private UserService userService;

    @Autowired
    public AngelController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/new/requests/{username}")
    public List<Request> getAllNewRequestsInArea(@PathVariable String username){
        return userService.getAllNewRequestsInArea(username);
    }

    @GetMapping("/requests")
    public List<Request> getAllRequestsAssignedToAngel(@RequestParam String username){
        return userService.getAllRequestsAssignedToAngel(username);
    }

    @PutMapping("/add/request")
    public List<Request> addRequestToAngel(@RequestParam Long requestId, @RequestParam Long angelId){
        return userService.addRequestToAngel(requestId, angelId);
    }

    @DeleteMapping("/remove/request")
    public List<Request> removeRequestAssignedTOAngel(@RequestParam Long requestId, @RequestParam Long angelId){
        return userService.removeRequestAssignedTOAngel(requestId, angelId);
    }

    @PutMapping("/request/status")
    public void updateReqStatusAsComplete(@RequestParam Long requestId){
        userService.updateReqStatusAsComplete(requestId);
    }


}
