package com.nvc.backendtest.service;

import com.nvc.backendtest.dataaccess.UserRepository;
import com.nvc.backendtest.exceptions.UserAlreadyExistsException;
import com.nvc.backendtest.exceptions.UserNotFoundException;
import com.nvc.backendtest.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.nvc.backendtest.util.Messages.USER_ALREADY_EXISTS_ERROR;
import static com.nvc.backendtest.util.Messages.USER_NOT_FOUND_ERROR;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> retrieveUsers(String email) {
        List<User> userList;
        if (email != null && !email.isEmpty()) {
            userList = userRepository.findByEmail(email);
            if (userList == null || userList.isEmpty()) {
                throw new UserNotFoundException(USER_NOT_FOUND_ERROR);
            }
        } else {
            userList = userRepository.findAll();
        }
        return userList;
    }

    @Override
    public User upsertUser(User user) {
        List<User> existingUsers = userRepository.findByEmail(user.getEmail());
        if (existingUsers == null || existingUsers.isEmpty()){
            user = userRepository.save(user);
        } else {
            throw new UserAlreadyExistsException(USER_ALREADY_EXISTS_ERROR, user.getEmail());
        }

        return user;
    }
}
