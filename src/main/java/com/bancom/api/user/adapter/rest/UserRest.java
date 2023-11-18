package com.bancom.api.user.adapter.rest;

import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.port.output.UserApiPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRest implements UserApiPort {

    @Override
    @GetMapping
    public List<User> list() {
        return new ArrayList<>();
    }

}
