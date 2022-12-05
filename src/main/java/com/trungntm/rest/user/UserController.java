package com.trungntm.rest.user;

import com.trungntm.domain.entity.user.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserController {

    String API_PATH = "/api/v1/user";

    @GetMapping("/{id}")
    User get(@PathVariable Long id);
}
