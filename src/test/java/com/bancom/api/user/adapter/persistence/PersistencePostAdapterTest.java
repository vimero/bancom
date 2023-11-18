package com.bancom.api.user.adapter.persistence;


import com.bancom.api.user.adapter.persistence.mysql.entity.PostEntity;
import com.bancom.api.user.adapter.persistence.mysql.entity.UserEntity;
import com.bancom.api.user.adapter.persistence.mysql.repository.PostRepository;
import com.bancom.api.user.adapter.persistence.mysql.repository.UserRepository;
import com.bancom.api.user.application.domain.Post;
import com.bancom.api.user.application.exception.NotFoundException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PersistencePostAdapterTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PersistencePostAdapter persistencePostAdapter;

    private PostEntity postEntity;

    private UserEntity userEntity;


    @BeforeEach
    public void setup(){
        postEntity = PostEntity.builder()
                .id(21L)
                .dateCreated(LocalDateTime.now())
                .build();

        userEntity = UserEntity.builder()
                .id(21L)
                .dateCreated(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("Validate beans in spring context")
    void injectedComponentsAreNotNull(){
        assertThat(postRepository).isNotNull();
        assertThat(persistencePostAdapter).isNotNull();
    }


    @Test
    @DisplayName("Create post")
    public void givenPostObject_whenCreatePost_thenReturnPostUpdated(){
        Post postNew = Post.builder()
                .id(40L)
                .text("Hello World")
                .user(21L)
                .build();
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        when(postRepository.save(any(PostEntity.class))).thenReturn(postEntity);

        Post post = persistencePostAdapter.createPost(postNew);

        assertThat(postEntity).isNotNull();
        assertThat(post).isNotNull();

    }

    @Test
    @DisplayName("Update post")
    public void givenPostObject_whenUpdatePost_thenReturnPostUpdated(){
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(postEntity));
        persistencePostAdapter.updatePost(21L, "Hello World");
        assertThat(postEntity.getText()).isEqualTo("Hello World");
        assertThat(postEntity.getDateUpdated()).isNotNull();
    }

    @Test
    @DisplayName("Update post when id is not found")
    public void givenPostObject_whenUpdatePost_thenReturnNotFoundException(){
        when(postRepository.findById(anyLong())).thenReturn(Optional.empty());
        Post post = Post.builder().text("Hello").build();
        Exception exception = assertThrows(NotFoundException.class, () -> {
            persistencePostAdapter.updatePost(22L, "Hello World");
        });

        String expectedMessage = "Post not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Remove post")
    public void givenPostObject_whenRemovePost_thenPostNotFound(){
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(postEntity));
        persistencePostAdapter.removePost(21L);
    }

}
