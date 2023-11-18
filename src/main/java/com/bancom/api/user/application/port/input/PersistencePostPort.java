package com.bancom.api.user.application.port.input;

import com.bancom.api.user.application.domain.Post;
import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.exception.NotFoundException;
import com.bancom.api.user.application.exception.RuleViolatedException;

import java.util.List;

public interface PersistencePostPort {

    List<Post> getPosts();

    Post createPost(Post post);

    Post updatePost(Long id, Long user, String text) throws NotFoundException, RuleViolatedException;

    Post removePost(Long id) throws NotFoundException;

}
