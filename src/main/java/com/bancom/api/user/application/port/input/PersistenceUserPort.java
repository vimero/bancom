package com.bancom.api.user.application.port.input;

import com.bancom.api.user.application.exception.NotFoundException;
import com.bancom.api.user.application.domain.User;

import java.util.List;

public interface PersistenceUserPort {

    List<User> getUsers();

    User createUser(User user);

    User updateUser(Long id, User user) throws NotFoundException;

}
