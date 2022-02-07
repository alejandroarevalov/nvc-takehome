package com.nvc.backendtest.restcontroller;

import com.nvc.backendtest.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserControllerImplTest {

    @Autowired
    private UserController userController;

    @Test
    public void shouldInsertUser() {
        UserDTO newUserDTO = UserDTO.builder()
                .firstName("Alejandro")
                .lastName("Arevalo")
                .email("alejandroarevalov@gmail.com")
                .build();

       userController.upsertUser(newUserDTO);

        //Assertions.assertEquals("User created successfully", result);
    }
}
