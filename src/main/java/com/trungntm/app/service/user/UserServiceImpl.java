package com.trungntm.app.service.user;

import com.trungntm.app.converter.user.UserConverter;
import com.trungntm.app.service.keycloak.DTO.UserKeycloakCreationDTO;
import com.trungntm.app.service.keycloak.UserKeycloakService;
import com.trungntm.domain.entity.user.User;
import com.trungntm.infrastructure.exception.KeycloakCreationException;
import com.trungntm.infrastructure.repository.UserRepository;
import com.trungntm.rest.auth.UserCreationDTO;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserConverter converter;

    @Autowired
    private UserRepository repo;

    @Autowired
    private UserKeycloakService userKeycloakService;

    @Override
    @Transactional
    public User create(UserCreationDTO dto) {
        UserKeycloakCreationDTO kcUserDTO = UserKeycloakCreationDTO.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        Map<String, List<String>> attributes = new LinkedHashMap<>(0);
        dto.getAttributes().forEach((k, v) -> {
            attributes.put(k, Collections.singletonList(v.toString()));
        });

        kcUserDTO.setAttributes(attributes);

        try (Response res = userKeycloakService.create(kcUserDTO)) {

            switch (res.getStatus()) {
                case 201 -> {
                    UserRepresentation kcUserCreated = userKeycloakService.getByUsername(dto.getUsername());

                    User toCreate = converter.fromUserCreationDTO(dto);
                    toCreate.setExternalId(kcUserCreated.getId());
                    toCreate.setEnabled(true);
                    User userCreated = repo.persistAndFlush(toCreate);
                    return userCreated;
                }
                case 409 -> {
                    UserRepresentation kcUserCreated = userKeycloakService.getByUsername(dto.getUsername());
                    userKeycloakService.remove(kcUserCreated);
                    throw new KeycloakCreationException("Users have already existed!");
                }
                default -> {
                    UserRepresentation kcUserCreated = userKeycloakService.getByUsername(dto.getUsername());
                    userKeycloakService.remove(kcUserCreated);
                    throw new RuntimeException("Unknown exception");
                }
            }

        } catch (Exception ex) {
            UserRepresentation kcUserCreated = userKeycloakService.getByUsername(dto.getUsername());
            userKeycloakService.remove(kcUserCreated);
            throw ex;
        }
    }

    @Override
    public Optional<User> get(Long id) {
        return repo.findById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return repo.findByUsernameAndEnabled(username, true);
    }

    @Override
    public UserConverter getConverter() {
        return converter;
    }
}
