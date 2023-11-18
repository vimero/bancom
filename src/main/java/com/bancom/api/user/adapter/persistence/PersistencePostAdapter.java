package com.bancom.api.user.adapter.persistence;

import com.bancom.api.user.adapter.persistence.mysql.entity.PostEntity;
import com.bancom.api.user.adapter.persistence.mysql.entity.UserEntity;
import com.bancom.api.user.adapter.persistence.mysql.repository.PostRepository;
import com.bancom.api.user.adapter.persistence.mysql.repository.UserRepository;
import com.bancom.api.user.application.domain.Post;
import com.bancom.api.user.application.exception.NotFoundException;
import com.bancom.api.user.application.exception.RuleViolatedException;
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
    private UserRepository userRepository;

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll().stream()
                .map(entity -> toDTO(entity))
                .collect(toCollection(ArrayList::new));
    }

    @Override
    public Post createPost(Post post) {
        UserEntity userEntity = findUserById(post.getUser());

        PostEntity postEntity = PostEntity.builder()
                .id(post.getId())
                .text(post.getText())
                .dateCreated(LocalDateTime.now())
                .user(userEntity)
                .build();

        postEntity = postRepository.save(postEntity);


        return toDTO(postEntity);
    }

    @Override
    public Post updatePost(Long id, Long user, String text) throws NotFoundException, RuleViolatedException {
        PostEntity postEntity = findById(id);
        if( postEntity.getUser().getId().equals(user)){
            postEntity.setText(text);
            postEntity.setDateUpdated(LocalDateTime.now());
            return toDTO(postEntity);
        }
        throw new RuleViolatedException("User cannot post updated to other author");
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

    public UserEntity findUserById(Long id) throws NotFoundException {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(!userEntity.isPresent()){
            throw new NotFoundException("User not found");
        }

        return userEntity.get();
    }

}
