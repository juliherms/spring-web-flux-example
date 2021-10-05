package com.github.juliherms.spring.web.flux.dao;

import com.github.juliherms.spring.web.flux.dto.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class UserDao {

    private static void sleepExecution(int i){

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }

    public List<User> getUsers(){
            return IntStream.rangeClosed(1,10)
                    .peek(UserDao::sleepExecution)
                    .peek(i -> System.out.println("processing count: " + i))
                    .mapToObj(i -> new User( Long.valueOf(i), "user" + i))
                    .collect(Collectors.toList());
    }

    public Flux<User> getUsersStreams() {
        return  Flux.range(1,10)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                .map(i -> new User(Long.valueOf(i),"user" + 1));
    }

    public Flux<User> getUserList() {
            return Flux.range(1,50)
                    .doOnNext(i -> System.out.println("processing count in stream flow : " + i))
                    .map(i -> new User(Long.valueOf(i),"user" + i));
    }

}
