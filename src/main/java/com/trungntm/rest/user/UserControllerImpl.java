package com.trungntm.rest.user;

import com.trungntm.app.service.user.UserService;
import com.trungntm.domain.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UserController.API_PATH)
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService service;

    @Override
    public User get(@PathVariable Long id) {
        return service.get(id).orElse(null);
    }
}
