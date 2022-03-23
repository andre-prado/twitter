package br.com.andreprado.twitter.controllers;

import br.com.andreprado.twitter.repositories.UserMetricsRepository;
import br.com.andreprado.twitter.repositories.UserRepository;
import br.com.andreprado.twitter.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    final UserService service;

    final UserRepository repository;
    final UserMetricsRepository metricsRepository;

    public UserController(UserService service, UserRepository repository, UserMetricsRepository metricsRepository) {
        this.service = service;
        this.repository = repository;
        this.metricsRepository = metricsRepository;
    }

    @GetMapping("users/top-five")
    public ResponseEntity getTopFive() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getTopFive());
    }
}
