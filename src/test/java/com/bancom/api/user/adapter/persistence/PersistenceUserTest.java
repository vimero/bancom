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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
                .build();

        userEntityLocal = UserEntity.builder()
                .id(22L)
                .build();
    }

    @Test
    @DisplayName("Validate beans in spring context")
    void injectedComponentsAreNotNull(){
        assertThat(userRepository).isNotNull();
    }

    @Test
    @DisplayName("List all user entities")
    public void givenUsersList_whenFindAll_thenUsersList(){

        userRepository.save(userEntity);
        userRepository.save(userEntityLocal);

        List<UserEntity> userEntityList = userRepository.findAll();

        assertThat(userEntityList).isNotNull();
        assertThat(userEntityList.size()).isEqualTo(2);

    }

}
