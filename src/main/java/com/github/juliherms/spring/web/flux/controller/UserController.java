package com.github.juliherms.spring.web.flux.controller;

import com.github.juliherms.spring.web.flux.dto.User;
import com.github.juliherms.spring.web.flux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getAllUsers(){
        return  service.loadAllUsers();
    }

    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAllUsersStream(){
        return service.loadAllUsersStream();
    }
}
