package com.bancom.api.user.application.usecase;

import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.port.input.PersistenceUserPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateUserUseCase {

    private PersistenceUserPort persistenceUserPort;

    public User create(User user){
        return persistenceUserPort.createUser(user);
    }

}
