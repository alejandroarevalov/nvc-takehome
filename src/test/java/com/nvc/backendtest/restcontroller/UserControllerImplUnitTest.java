package com.nvc.backendtest.restcontroller;

import com.nvc.backendtest.dto.UserDTO;
import com.nvc.backendtest.exceptions.UserAlreadyExistsException;
import com.nvc.backendtest.model.User;
import com.nvc.backendtest.service.UserService;
import com.nvc.backendtest.util.JsonResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.nvc.backendtest.util.JsonResponse.Fields.ERROR_MESSAGE;
import static com.nvc.backendtest.util.JsonResponse.Fields.SUCCESS_MESSAGE;
import static com.nvc.backendtest.util.Messages.USER_ALREADY_EXISTS_ERROR;
import static com.nvc.backendtest.util.Messages.USER_CREATED_SUCCESSFULLY;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserControllerImplUnitTest {

    @Mock
    private UserService userService;

    private UserControllerImpl userControllerImpl;
    private UserDTO userDTO;
    private User user;

    @BeforeEach
    void setUp() {
        userControllerImpl = new UserControllerImpl(userService);
        userControllerImpl.setModelMapper(new ModelMapper());
        userDTO = UserDTO.builder()
                .firstName("Dummy")
                .lastName("User")
                .email("dummyuser@nvc.com")
                .build();
        user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .build();
    }

    @Test
    void shouldUpsertUserSuccessfully() {
        //Given
        JsonResponse expectedResponse = new JsonResponse();
        expectedResponse.setField(SUCCESS_MESSAGE, format(USER_CREATED_SUCCESSFULLY, userDTO.getEmail()));

        //When
        when(userService.upsertUser(user)).thenReturn(user);
        JsonResponse actualResponse = userControllerImpl.upsertUser(userDTO);

        //Then
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);
        verify(userService).upsertUser(user);
    }

    @Test
    void shouldReturnUserAlreadyExistsErrorMessage() {
        //Given
        UserAlreadyExistsException expectedException = new UserAlreadyExistsException(USER_ALREADY_EXISTS_ERROR, userDTO.getEmail());
        JsonResponse expectedResponse = new JsonResponse();
        expectedResponse.setField(ERROR_MESSAGE, expectedException.getMessage());

        //When
        when(userService.upsertUser(user)).thenThrow(expectedException);
        UserAlreadyExistsException exceptionResult = assertThrows(
                UserAlreadyExistsException.class,
                () -> userControllerImpl.upsertUser(userDTO)
        );

        //Then
        assertNotNull(exceptionResult);
        assertEquals(expectedException, exceptionResult);
        verify(userService).upsertUser(user);
    }

    @Test
    void shouldReturnAllUsersWhenNoReceivingEmailString() {
        //Given
        List<User> entityUsersList = List.of(
            User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
            .build(),
            User.builder()
                .firstName("AnotherDummy")
                .lastName("User")
                .email("anotherdummyuser@nvc.com")
            .build()
        );
        List<UserDTO> expectedUsersList = List.of(userDTO, UserDTO.builder()
                .firstName("AnotherDummy")
                .lastName("User")
                .email("anotherdummyuser@nvc.com")
                .build()
        );

        //When
        when(userService.retrieveUsers(null)).thenReturn(entityUsersList);
        List<UserDTO> actualUserList = userControllerImpl.retrieveUsers(null);

        //Then
        assertNotNull(actualUserList);
        assertFalse(actualUserList.isEmpty());
        assertEquals(2, actualUserList.size());
        assertThat(actualUserList.containsAll(expectedUsersList));
        verify(userService).retrieveUsers(null);
    }
}
