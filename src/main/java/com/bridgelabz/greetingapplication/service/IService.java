package com.bridgelabz.greetingapplication.service;

import com.bridgelabz.greetingapplication.model.Greeting;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IService {
     Mono<Greeting> create(Greeting greeting);
    Flux<Greeting> getAll();
    Mono<Greeting>getById(int id);
    Mono<Greeting>update(Greeting greeting,int id);
    Mono<Void> delete(int id);
}
