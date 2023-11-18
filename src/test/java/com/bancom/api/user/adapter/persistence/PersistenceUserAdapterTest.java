package com.bancom.api.user.adapter.persistence;


import com.bancom.api.user.adapter.persistence.mysql.entity.UserEntity;
import com.bancom.api.user.adapter.persistence.mysql.repository.UserRepository;
import com.bancom.api.user.application.domain.User;
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
public class PersistenceUserAdapterTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private PersistenceUserAdapter persistenceUserAdapter;

    private UserEntity userEntity;

    @BeforeEach
    public void setup(){
        userEntity = UserEntity.builder()
                .id(21L)
                .dateCreated(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("Validate beans in spring context")
    void injectedComponentsAreNotNull(){
        assertThat(userRepository).isNotNull();
        assertThat(persistenceUserAdapter).isNotNull();
    }


    @Test
    @DisplayName("Update user")
    public void givenUserObject_whenUpdateUser_thenReturnUserUpdated(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        User user = User.builder().name("Vimero").build();
        persistenceUserAdapter.updateUser(21L, user);
        assertThat(userEntity.getName()).isEqualTo("Vimero");
        assertThat(userEntity.getDateUpdated()).isNotNull();
    }

    @Test
    @DisplayName("Update user when id is not found")
    public void givenUserObject_whenUpdateUser_thenReturnNotFoundException(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        User user = User.builder().name("Vimero").build();
        Exception exception = assertThrows(NotFoundException.class, () -> {
            persistenceUserAdapter.updateUser(22L, user);
        });

        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @DisplayName("Remove user")
    public void givenUserObject_whenRemoveUser_thenUserNotFound(){
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
        User user = User.builder().name("Vimero").build();
        persistenceUserAdapter.removeUser(21L);
    }

}
