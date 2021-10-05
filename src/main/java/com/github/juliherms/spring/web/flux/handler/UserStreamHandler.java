package com.github.juliherms.spring.web.flux.handler;

import com.github.juliherms.spring.web.flux.dao.UserDao;
import com.github.juliherms.spring.web.flux.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserStreamHandler {

    @Autowired
    private UserDao dao;

    public Mono<ServerResponse> getUsers(ServerRequest request){
        Flux<User> usersStream = dao.getUsersStreams();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(usersStream,User.class);
    }
}
