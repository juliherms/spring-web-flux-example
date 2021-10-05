package com.github.juliherms.spring.web.flux.service;

import com.github.juliherms.spring.web.flux.dao.UserDao;
import com.github.juliherms.spring.web.flux.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao dao;

    public List<User> loadAllUsers(){
        long start = System.currentTimeMillis();
        List<User> users = dao.getUsers();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time: " + (end - start));
        return users;
    }

    public Flux<User> loadAllUsersStream(){
        long start = System.currentTimeMillis();
        Flux<User> users = dao.getUsersStreams();
        long end = System.currentTimeMillis();
        System.out.println("Total execution time : " + (end - start));
        return users;
    }

}
