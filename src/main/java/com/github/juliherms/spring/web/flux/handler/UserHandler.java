package com.github.juliherms.spring.web.flux.handler;

import com.github.juliherms.spring.web.flux.dao.UserDao;
import com.github.juliherms.spring.web.flux.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserHandler {

    @Autowired
    private UserDao dao;

    public Mono<ServerResponse> loadUsers(ServerRequest request){
        Flux<User> userList = dao.getUserList();
        return ServerResponse.ok().body(userList,User.class);
    }

    public Mono<ServerResponse> findUser(ServerRequest request){
        //capture and get id from path
        Long userId = Long.valueOf(request.pathVariable("id"));
        //filter user by id
        Mono<User> userMono = dao.getUserList().filter(u -> u.getId() == userId).next();
        return ServerResponse.ok().body(userMono,User.class);
    }

    public Mono<ServerResponse> saveUser(ServerRequest request){
        Mono<User> userMono = request.bodyToMono(User.class);
        Mono<String> saveResponse = userMono.map((dto -> dto.getId() + ":" + dto.getName()));
        return ServerResponse.ok().body(saveResponse,String.class);

    }
}
