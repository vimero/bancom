package com.bancom.api.user.adapter.persistence;


import com.bancom.api.user.adapter.persistence.mysql.entity.PostEntity;
import com.bancom.api.user.adapter.persistence.mysql.entity.UserEntity;
import com.bancom.api.user.adapter.persistence.mysql.repository.PostRepository;
import com.bancom.api.user.adapter.persistence.mysql.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class PersistencePostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private PostEntity postEntity;

    private UserEntity userEntity;

    private PostEntity postEntityLocal;

    @BeforeEach
    public void setup(){
        postEntity = PostEntity.builder()
                .id(21L)
                .dateCreated(LocalDateTime.now())
                .build();

        postEntityLocal = PostEntity.builder()
                .id(22L)
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
    }

    @Test
    @DisplayName("List all posts")
    public void givenPostsList_whenFindAll_thenPostsList(){
        userRepository.save(userEntity);
        postEntity.setUser(userEntity);
        postEntityLocal.setUser(userEntity);

        postRepository.save(postEntity);
        postRepository.save(postEntityLocal);

        List<PostEntity> postEntityList = postRepository.findAll();

        assertThat(postEntityList).isNotNull();
        assertThat(postEntityList.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("Save post")
    public void givenPostObject_whenSavePost_thenReturnPostObject(){
        userRepository.save(userEntity);
        postEntity.setUser(userEntity);
        postRepository.save(postEntity);
        PostEntity postEntityFind = postRepository.findById(21L).get();

        assertThat(postEntityFind).isNotNull();
    }

    @Test
    @DisplayName("Save post with date created null")
    public void givenPostObject_whenSavePost_thenReturnException(){
        Exception exception = assertThrows(NullPointerException.class, () -> {
            postEntity.setDateCreated(null);
            postRepository.save(postEntity);
        });

        String expectedMessage = "dateCreated is marked non-null but is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

}
