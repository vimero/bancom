package com.bancom.api.user.adapter.persistence;

import com.bancom.api.user.adapter.persistence.mysql.entity.PostEntity;
import com.bancom.api.user.adapter.persistence.mysql.repository.PostRepository;
import com.bancom.api.user.application.domain.Post;
import com.bancom.api.user.application.exception.NotFoundException;
import com.bancom.api.user.application.port.input.PersistencePostPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toCollection;

@Service
@AllArgsConstructor
public class PersistencePostAdapter implements PersistencePostPort {

    private PostRepository postRepository;

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll().stream()
                .map(entity -> toDTO(entity))
                .collect(toCollection(ArrayList::new));
    }

    @Override
    public Post createPost(Post post) {
        PostEntity postEntity = toEntity(post);
        postEntity.setDateCreated(LocalDateTime.now());

        postEntity = postRepository.save(postEntity);


        return toDTO(postEntity);
    }

    @Override
    public Post updatePost(Long id, String text) throws NotFoundException {
        PostEntity postEntity = findById(id);
        postEntity.setText(text);
        postEntity.setDateUpdated(LocalDateTime.now());
        return toDTO(postEntity);

    }

    @Override
    public Post removePost(Long id) throws NotFoundException {
        PostEntity postEntity =findById(id);
        postRepository.delete(postEntity);
        return toDTO(postEntity);
    }

    private Post toDTO(PostEntity postEntity){
        return Post.builder()
                .id(postEntity.getId())
                .text(postEntity.getText())
                .build();

    }

    private PostEntity toEntity(Post post){
        return PostEntity.builder()
                .id(post.getId())
                .text(post.getText())
                .build();
    }


    public PostEntity findById(Long id) throws NotFoundException {
        Optional<PostEntity> postEntity = postRepository.findById(id);
        if(!postEntity.isPresent()){
            throw new NotFoundException("Post not found");
        }

        return postEntity.get();
    }

}
