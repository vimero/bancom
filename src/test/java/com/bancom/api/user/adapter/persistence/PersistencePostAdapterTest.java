package com.bancom.api.user.adapter.persistence;


import com.bancom.api.user.adapter.persistence.mysql.entity.PostEntity;
import com.bancom.api.user.adapter.persistence.mysql.repository.PostRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PersistencePostAdapterTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PersistencePostAdapter persistencePostAdapter;

    private PostEntity postEntity;

    @BeforeEach
    public void setup(){
        postEntity = PostEntity.builder()
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
