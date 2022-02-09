package com.nvc.backendtest.restcontroller;

import com.nvc.backendtest.dto.UserDTO;
import com.nvc.backendtest.util.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@RequestMapping(value = "/users")
public interface UserController {

    @PutMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    JsonResponse upsertUser(@RequestBody UserDTO userDTO);

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    List<UserDTO> retrieveUsers(@RequestParam(name = "email", required = false) String email);
}
