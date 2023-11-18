package com.bancom.api.user.adapter.persistence;

import com.bancom.api.user.application.exception.NotFoundException;
import com.bancom.api.user.adapter.persistence.mysql.entity.UserEntity;
import com.bancom.api.user.adapter.persistence.mysql.repository.UserRepository;
import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.port.input.PersistenceUserPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toCollection;

@Service
@AllArgsConstructor
public class PersistenceUserAdapter implements PersistenceUserPort {

    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll().stream()
                .map(entity -> toDTO(entity))
                .collect(toCollection(ArrayList::new));

    }

    @Override
    public User createUser(User user) {
        UserEntity userEntity = toEntity(user);
        userEntity.setDateCreated(LocalDateTime.now());

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

    @Override
    public User updateUser(Long id, User user) throws NotFoundException {
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if(userEntity.isPresent()){
            UserEntity entity = userEntity.get();
            entity.setName(user.getName());
            entity.setLastName(user.getLastName());
            entity.setCellphone(user.getCellphone());
            entity.setPassword(user.getPassword());
            entity.setDateUpdated(LocalDateTime.now());
            return toDTO(entity);
        }
        throw new NotFoundException("User not found");

    }

    private User toDTO(UserEntity userEntity){
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .lastName(userEntity.getLastName())
                .cellphone(userEntity.getCellphone())
                .password(userEntity.getPassword())
                .build();

    }

    private UserEntity toEntity(User user){
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .cellphone(user.getCellphone())
                .password(user.getPassword())
                .build();
    }

}
