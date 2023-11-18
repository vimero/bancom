package com.bancom.api.user.application.usecase;

import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.exception.NotFoundException;
import com.bancom.api.user.application.port.input.PersistenceUserPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdateUserUseCase {

    private PersistenceUserPort persistenceUserPort;

    public User update(Long id, User user) throws NotFoundException {
        return persistenceUserPort.updateUser(id, user);
    }

}
