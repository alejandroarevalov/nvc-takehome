package com.nvc.backendtest.service;

import com.nvc.backendtest.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> retrieveUsers(String email);

    User upsertUser(User user);
}
