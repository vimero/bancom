package com.bancom.api.user.infrastructure;

import com.bancom.api.user.application.port.input.PersistenceUserPort;
import com.bancom.api.user.application.usecase.user.CreateUserUseCase;
import com.bancom.api.user.application.usecase.user.ListUserUseCase;
import com.bancom.api.user.application.usecase.user.RemoveUserUseCase;
import com.bancom.api.user.application.usecase.user.UpdateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    ListUserUseCase listUserUseCase(PersistenceUserPort persistenceUserPort){
        return new ListUserUseCase(persistenceUserPort);
    }

    @Bean
    CreateUserUseCase createUserUseCase(PersistenceUserPort persistenceUserPort){
        return new CreateUserUseCase(persistenceUserPort);
    }

    @Bean
    UpdateUserUseCase updateUserUseCase(PersistenceUserPort persistenceUserPort){
        return new UpdateUserUseCase(persistenceUserPort);
    }

    @Bean
    RemoveUserUseCase removeUserUseCase(PersistenceUserPort persistenceUserPort){
        return new RemoveUserUseCase(persistenceUserPort);
    }

}
