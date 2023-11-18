package com.bancom.api.user.adapter.rest;

import com.bancom.api.user.application.domain.Post;
import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.port.output.PostApiPort;
import com.bancom.api.user.application.port.output.UserApiPort;
import com.bancom.api.user.application.usecase.post.CreatePostUseCase;
import com.bancom.api.user.application.usecase.post.ListPostUseCase;
import com.bancom.api.user.application.usecase.post.RemovePostUseCase;
import com.bancom.api.user.application.usecase.post.UpdatePostUseCase;
import com.bancom.api.user.application.usecase.user.CreateUserUseCase;
import com.bancom.api.user.application.usecase.user.ListUserUseCase;
import com.bancom.api.user.application.usecase.user.RemoveUserUseCase;
import com.bancom.api.user.application.usecase.user.UpdateUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostRest implements PostApiPort {


    private ListPostUseCase listPostUseCase;
    private CreatePostUseCase createPostUseCase;
    private UpdatePostUseCase updatePostUseCase;
    private RemovePostUseCase removePostUseCase;

    @Override
    @GetMapping
    public List<Post> list() {
        return listPostUseCase.getPosts();
    }

    @Override
    @PostMapping
    public Post create(Post post) {
        return createPostUseCase.create(post);
    }

    @Override
    @PutMapping("/{id}/users/{user}")
    public Post update(@PathVariable Long id,  @PathVariable Long user, @RequestBody Post post) {
        return updatePostUseCase.update(id, user, post.getText());
    }

    @Override
    @DeleteMapping("/{id}")
    public Post remove(@PathVariable Long id) {
        return removePostUseCase.remove(id);
    }

}
