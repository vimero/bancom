package com.bancom.api.user.application.usecase.post;

import com.bancom.api.user.application.domain.Post;
import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.port.input.PersistencePostPort;
import com.bancom.api.user.application.port.input.PersistenceUserPort;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ListPostUseCase {

    private PersistencePostPort persistencePostPort;

    public List<Post> getPosts(){
       return persistencePostPort.getPosts();
    }

}
