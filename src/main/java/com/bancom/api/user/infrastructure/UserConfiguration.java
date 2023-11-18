package com.bancom.api.user.infrastructure;

import com.bancom.api.user.application.port.input.PersistenceUserPort;
import com.bancom.api.user.application.usecase.ListUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    ListUserUseCase listUserUseCase(PersistenceUserPort persistenceUserPort){
        return new ListUserUseCase(persistenceUserPort);
    }

}
