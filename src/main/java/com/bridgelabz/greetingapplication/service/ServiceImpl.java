package com.bridgelabz.greetingapplication.service;

import com.bridgelabz.greetingapplication.model.Greeting;
import com.bridgelabz.greetingapplication.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ServiceImpl implements IService{

    @Autowired
    private GreetingRepository greetingRepository;
    @Override
    public Mono<Greeting> create(Greeting greeting) {
         return greetingRepository.save(greeting);
    }

    @Override
    public Flux<Greeting> getAll() {
        return greetingRepository.findAll();
    }

    @Override
    public Mono<Greeting> getById(int id) {
        return greetingRepository.findById(id);
    }

    @Override
    public Mono<Greeting> update(Greeting greeting, int id) {
        Mono<Greeting>oldGreet=greetingRepository.findById(id);
        return oldGreet.flatMap(greet1->{
            greet1.setName(greeting.getName());
            greet1.setMsg(greeting.getMsg());
            return greetingRepository.save(greet1);
        });

    }

    @Override
    public Mono<Void> delete(int id) {
        return greetingRepository.deleteById(id);
    }
}
