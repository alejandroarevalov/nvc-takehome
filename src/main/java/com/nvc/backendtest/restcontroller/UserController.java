package com.nvc.backendtest.restcontroller;

import com.nvc.backendtest.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping(value = "/users")
public interface UserController {

    @PutMapping
    ResponseEntity upsertUser(@RequestBody UserDTO userDTO);

    @GetMapping
    @ResponseBody
    List<UserDTO> retrieveUsers(@RequestParam(name = "email", required = false) String email);
}
