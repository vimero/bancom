package com.bancom.api.user.adapter.persistence;

import com.bancom.api.user.adapter.persistence.mysql.entity.UserEntity;
import com.bancom.api.user.adapter.persistence.mysql.repository.UserRepository;
import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.port.input.PersistenceUserPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@Service
@AllArgsConstructor
public class PersistenceUserAdapter implements PersistenceUserPort {

    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll().stream()
                .map(entity -> User.builder().id(entity.getId())
                        .name(entity.getName())
                        .name(entity.getName())
                        .lastName(entity.getLastName())
                        .cellphone(entity.getCellphone())
                        .password(entity.getPassword())
                        .build())
                .collect(toCollection(ArrayList::new));

    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .cellphone(user.getCellphone())
                .password(user.getPassword())
                .dateCreated(LocalDateTime.now())
                .build();

        userEntity = userRepository.save(userEntity);


        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .name(userEntity.getName())
                .lastName(userEntity.getLastName())
                .cellphone(userEntity.getCellphone())
                .password(userEntity.getPassword())
                .build();
    }

}
