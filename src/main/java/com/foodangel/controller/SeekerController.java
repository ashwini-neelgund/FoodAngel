package com.foodangel.controller;

import com.foodangel.model.Request;
import com.foodangel.model.User;
import com.foodangel.service.RequestService;
import com.foodangel.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seeker")
@CrossOrigin
@Log
public class SeekerController {

    private RequestService requestService;

    @Autowired
    public SeekerController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/request")
    public Request getRequest(@RequestParam Long id,@RequestParam int pin){
        return requestService.getRequest(id,pin);
    }

    @PostMapping("/add/request")
    public Request addRequest(@RequestParam User user){
        return requestService.addRequest(user);
    }

    @PutMapping("/update/request")
    public Request updateRequest(@RequestParam Request request){
        return requestService.updateRequest(request);
    }

    @DeleteMapping("/remove/request")
    public void deleteRequest(@RequestParam Long requestId){
        requestService.removeRequest(requestId);
    }

    @GetMapping("/check/angel/{zipCode}")
    public boolean checkForAngel(@PathVariable("zipCode") int zipcode){
        if(requestService.checkForAngel(zipcode)){
            return true;
        }else
            return false;
    }
}
