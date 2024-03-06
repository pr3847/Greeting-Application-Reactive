package com.bridgelabz.greetingapplication.controller;

import com.bridgelabz.greetingapplication.model.Greeting;
import com.bridgelabz.greetingapplication.model.Response;
import com.bridgelabz.greetingapplication.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/greetings")
public class GreetingController {

    @Autowired
    private IService iService;

    @PostMapping
    public Mono<ResponseEntity<Response>> create(@RequestBody Greeting greeting) {
        return iService.create(greeting)
                .map(savedGreeting -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new Response(HttpStatus.CREATED.value(), "Greeting created successfully")))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to create greeting")));
    }

    @GetMapping
    public ResponseEntity<Flux<Greeting>> getAll() {
        return ResponseEntity.ok(iService.getAll());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Greeting>> getById(@PathVariable int id) {
        return iService.getById(id)
                .map(greeting -> ResponseEntity.ok(greeting))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Response>> update(@RequestBody Greeting greeting, @PathVariable int id) {
        return iService.update(greeting, id)
                .map(updatedGreeting -> ResponseEntity.ok(new Response(HttpStatus.OK.value(), "Greeting updated successfully")))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response(HttpStatus.NOT_FOUND.value(), "Greeting not found")));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Response>> delete(@PathVariable int id) {
        return iService.delete(id)
                .then(Mono.just(ResponseEntity.ok(new Response(HttpStatus.OK.value(), "Greeting deleted successfully"))))
                .onErrorReturn(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new Response(HttpStatus.NOT_FOUND.value(), "Greeting not found")));
    }

}

