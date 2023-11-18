package com.bancom.api.user.infrastructure;

import com.bancom.api.user.application.port.input.PersistencePostPort;
import com.bancom.api.user.application.usecase.post.CreatePostUseCase;
import com.bancom.api.user.application.usecase.post.ListPostUseCase;
import com.bancom.api.user.application.usecase.post.RemovePostUseCase;
import com.bancom.api.user.application.usecase.post.UpdatePostUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostConfiguration {


    @Bean
    ListPostUseCase listPostUseCase(PersistencePostPort persistencePostPort){
        return new ListPostUseCase(persistencePostPort);
    }

    @Bean
    CreatePostUseCase createPostUseCase(PersistencePostPort persistencePostPort){
        return new CreatePostUseCase(persistencePostPort);
    }

    @Bean
    UpdatePostUseCase updatePostUseCase(PersistencePostPort persistencePostPort){
        return new UpdatePostUseCase(persistencePostPort);
    }

    @Bean
    RemovePostUseCase removePostUseCase(PersistencePostPort persistencePostPort){
        return new RemovePostUseCase(persistencePostPort);
    }

}
