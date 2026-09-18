package org.xy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xy.common.Result;
import org.xy.entity.User;
import org.xy.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;
    @PostMapping("/login")
    public Result<String> login(@RequestBody User user){
        return userService.login(user);
    }
    @PostMapping("register")
    public Result<Void> register(@RequestBody User user){
        return userService.register(user);
    }
}
