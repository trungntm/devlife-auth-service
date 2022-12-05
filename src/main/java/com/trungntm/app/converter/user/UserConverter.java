package com.trungntm.app.converter.user;

import com.trungntm.Converter;
import com.trungntm.domain.entity.user.User;
import com.trungntm.rest.auth.AuthenticatedUserDTO;
import com.trungntm.rest.auth.UserCreationDTO;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private static Converter.Instance<UserCreationDTO, User> userCreationConverter = Converter.getConverter(UserCreationDTO.class, User.class);
    private static Converter.Instance<AuthenticatedUserDTO, User> authenticatedUserConverter = Converter.getConverter(AuthenticatedUserDTO.class, User.class);
    public User fromUserCreationDTO(UserCreationDTO dto) {
        return userCreationConverter.convert(dto);
    }

    public UserCreationDTO toUserCreationDTO(User entity) {
        return userCreationConverter.backwards(entity);
    }

    public AuthenticatedUserDTO toAuthenticatedUser(User entity) {
        return authenticatedUserConverter.backwards(entity);
    }
}
