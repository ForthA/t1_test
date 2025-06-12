package ru.tarasov.testing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.tarasov.testing.model.AuthRequest;

@RequestMapping("/api/auth")
public interface AuthController {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    String register(@RequestBody User user);

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    String login(@RequestBody AuthRequest authRequest);
}