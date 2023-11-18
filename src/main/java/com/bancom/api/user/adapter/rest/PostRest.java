package com.bancom.api.user.adapter.rest;

import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.port.output.UserApiPort;
import com.bancom.api.user.application.usecase.user.CreateUserUseCase;
import com.bancom.api.user.application.usecase.user.ListUserUseCase;
import com.bancom.api.user.application.usecase.user.RemoveUserUseCase;
import com.bancom.api.user.application.usecase.user.UpdateUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/post")
public class PostRest implements UserApiPort {


    @Override
    public List<User> list() {
        return null;
    }

    @Override
    public User create(User user) {
        return null;
    }

    @Override
    public User update(Long id, User user) {
        return null;
    }

    @Override
    public User remove(Long id) {
        return null;
    }

}
