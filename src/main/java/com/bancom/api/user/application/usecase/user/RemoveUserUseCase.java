package com.bancom.api.user.application.usecase.user;

import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.exception.NotFoundException;
import com.bancom.api.user.application.port.input.PersistenceUserPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemoveUserUseCase {

    private PersistenceUserPort persistenceUserPort;

    public User remove(long id) throws NotFoundException {
        return persistenceUserPort.removeUser(id);
    }

}
