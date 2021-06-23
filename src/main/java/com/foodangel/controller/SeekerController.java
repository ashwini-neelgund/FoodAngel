package com.foodangel.controller;

import com.foodangel.model.Item;
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

    @GetMapping("/request/{id}/{pin}")
    public Request getRequest(@PathVariable("id") Long id,@PathVariable("pin") int pin){
        return requestService.getRequest(id,pin);
    }

    @PostMapping("/add/request")
    public Request addRequest(@RequestBody User user){
        return requestService.addRequest(user);
    }

    @PostMapping("/update/request")
    public Request updateRequest(@RequestBody Request request){
        return requestService.updateRequest(request);
    }

    @DeleteMapping("/remove/request/{requestId}")
    public void deleteRequest(@PathVariable("requestId") Long requestId){
        requestService.removeRequest(requestId);
    }

    @GetMapping("/check/angel/{zipCode}")
    public boolean checkForAngel(@PathVariable("zipCode") int zipcode){
        if(requestService.checkForAngel(zipcode)){
            return true;
        }else
            return false;
    }

    @GetMapping("/items")
    public List<Item> getItems(){
        return requestService.getItems();
    }
}
