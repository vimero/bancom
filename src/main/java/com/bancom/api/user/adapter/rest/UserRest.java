package com.bancom.api.user.adapter.rest;

import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.port.output.UserApiPort;
import com.bancom.api.user.application.usecase.ListUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserRest implements UserApiPort {

    private ListUserUseCase listUserUseCase;

    @Override
    @GetMapping
    public List<User> list() {
        return listUserUseCase.getUsers();
    }

}
