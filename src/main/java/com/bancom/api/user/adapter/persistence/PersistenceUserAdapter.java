package com.bancom.api.user.adapter.persistence;

import com.bancom.api.user.adapter.persistence.mysql.repository.UserRepository;
import com.bancom.api.user.application.domain.User;
import com.bancom.api.user.application.port.input.PersistenceUserPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
                .map(entity -> User.builder().id(entity.getId()).build()).collect(toCollection(ArrayList::new));

    }

}
