package com.trungntm.app.service.user;

import com.trungntm.app.converter.user.UserConverter;
import com.trungntm.domain.entity.user.User;
import com.trungntm.rest.auth.UserCreationDTO;

import java.util.Optional;

public interface UserService {

    User create(UserCreationDTO dto);

    Optional<User> get(Long id);

    Optional<User> getByUsername(String username);

    UserConverter getConverter();
}
