package com.bancom.api.user.adapter.rest;

import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.port.output.UserApiPort;
import com.bancom.api.user.application.usecase.CreateUserUseCase;
import com.bancom.api.user.application.usecase.ListUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserRest implements UserApiPort {

    private ListUserUseCase listUserUseCase;
    private CreateUserUseCase createUserUseCase;

    @Override
    @GetMapping
    public List<User> list() {
        return listUserUseCase.getUsers();
    }

    @Override
    @PostMapping
    public User create(@RequestBody User user) {
        return createUserUseCase.create(user);
    }

}
