package com.workintech.S19d2.controller;

import com.workintech.S19d2.dto.MemberRequest;
import com.workintech.S19d2.entity.Member;
import com.workintech.S19d2.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public Member register(@RequestBody MemberRequest memberRequest){
        return authenticationService.register(memberRequest.email(), memberRequest.password());

    }
}
