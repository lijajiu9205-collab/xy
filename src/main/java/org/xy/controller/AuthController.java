package org.xy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xy.Bo.LoginBo;
import org.xy.common.Result;
import org.xy.entity.User;
import org.xy.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AuthController {
    private final UserService userService;
    @PostMapping("/login")
    public Result<String> loginByPassword(@RequestBody LoginBo loginBo){
        return userService.loginByPassword(loginBo);
    }
    @PostMapping("/register")
    public Result<String> register(@RequestBody LoginBo loginBo){
        return userService.register(loginBo);
    }
    @PostMapping("/code")
    public Result<String>sendCode(@RequestBody User user){
        return userService.sendCode(user.getPhone());
    }
    @PostMapping("/login/phone")
    public Result<String> loginByPhone(@RequestBody LoginBo loginBo){return userService.loginByPhone(loginBo);}
}
