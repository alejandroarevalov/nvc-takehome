package com.nvc.backendtest.restcontroller;

import com.nvc.backendtest.dto.UserDTO;
import com.nvc.backendtest.model.User;
import com.nvc.backendtest.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

@RestController
public class UserControllerImpl extends AbstractNVCController<User, UserDTO> implements UserController {

    private UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity upsertUser(UserDTO userDTO) {
        User user = toEntity(userDTO, User.class);
        userService.upsertUser(user);
        Map<String, Object> body = new HashMap<>();
        body.put("message", format("User %s created succesfully", user.getEmail()));
        return new ResponseEntity(body, HttpStatus.CREATED);
    }

    @Override
    public List<UserDTO> retrieveUsers(String email) {
        List<User> users = userService.retrieveUsers(email);
        return users.stream()
                .map(user -> toDTO(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
