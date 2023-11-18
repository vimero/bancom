package com.bancom.api.user.adapter.persistence;


import com.bancom.api.user.adapter.persistence.mysql.entity.UserEntity;
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
public class PersistenceUserTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntity;

    private UserEntity userEntityLocal;

    @BeforeEach
    public void setup(){
        userEntity = UserEntity.builder()
                .id(21L)
                .dateCreated(LocalDateTime.now())
                .build();

        userEntityLocal = UserEntity.builder()
                .id(22L)
                .dateCreated(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("Validate beans in spring context")
    void injectedComponentsAreNotNull(){
        assertThat(userRepository).isNotNull();
    }

    @Test
    @DisplayName("List all users")
    public void givenUsersList_whenFindAll_thenUsersList(){
        userRepository.save(userEntity);
        userRepository.save(userEntityLocal);

        List<UserEntity> userEntityList = userRepository.findAll();

        assertThat(userEntityList).isNotNull();
        assertThat(userEntityList.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("Save user")
    public void givenUserObject_whenSaveUser_thenReturnUserObject(){
        userRepository.save(userEntity);
        UserEntity userEntityFind = userRepository.findById(21L).get();

        assertThat(userEntityFind).isNotNull();
    }

    @Test
    @DisplayName("Save user with date created null")
    public void givenUserObject_whenSaveUser_thenReturnException(){
        Exception exception = assertThrows(NullPointerException.class, () -> {
            userEntity.setDateCreated(null);
            userRepository.save(userEntity);
        });

        String expectedMessage = "dateCreated is marked non-null but is null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

}
