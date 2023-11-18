package com.bancom.api.user.application.usecase;

import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.port.input.PersistenceUserPort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListUserUseCase {

    private PersistenceUserPort persistenceUserPort;

    public List<User> getUsers(){
       return persistenceUserPort.getUsers();
    }

}
