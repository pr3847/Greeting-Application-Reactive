package com.bridgelabz.greetingapplication.repository;

import com.bridgelabz.greetingapplication.model.Greeting;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GreetingRepository extends ReactiveCrudRepository<Greeting,Integer> {
}
