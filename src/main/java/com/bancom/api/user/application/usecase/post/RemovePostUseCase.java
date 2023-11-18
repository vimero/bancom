package com.bancom.api.user.application.usecase.post;

import com.bancom.api.user.application.domain.Post;
import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.exception.NotFoundException;
import com.bancom.api.user.application.port.input.PersistencePostPort;
import com.bancom.api.user.application.port.input.PersistenceUserPort;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RemovePostUseCase {

    private PersistencePostPort persistencePostPort;

    public Post remove(long id) throws NotFoundException {
        return persistencePostPort.removePost(id);
    }

}
