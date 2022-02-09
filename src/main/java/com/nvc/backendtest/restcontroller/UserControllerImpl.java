package com.nvc.backendtest.restcontroller;

import com.nvc.backendtest.dto.UserDTO;
import com.nvc.backendtest.model.User;
import com.nvc.backendtest.service.UserService;
import com.nvc.backendtest.util.JsonResponse;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.nvc.backendtest.util.JsonResponse.Fields.SUCCESS_MESSAGE;
import static com.nvc.backendtest.util.Messages.USER_CREATED_SUCCESSFULLY;
import static java.lang.String.format;

@RestController
public class UserControllerImpl extends AbstractNVCController<User, UserDTO> implements UserController {

    private UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public JsonResponse upsertUser(UserDTO userDTO) {
        User user = toEntity(userDTO, User.class);
        userService.upsertUser(user);
        JsonResponse response = new JsonResponse();
        response.setField(SUCCESS_MESSAGE, format(USER_CREATED_SUCCESSFULLY, user.getEmail()));
        return response;
    }

    @Override
    public List<UserDTO> retrieveUsers(String email) {
        List<User> users = userService.retrieveUsers(email);
        return users.stream()
                .map(user -> toDTO(user, UserDTO.class))
                .collect(Collectors.toList());
    }
}
