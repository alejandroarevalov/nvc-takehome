package com.nvc.backendtest.exceptions;

import com.nvc.backendtest.util.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.nvc.backendtest.util.JsonResponse.Fields.ERROR_MESSAGE;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public JsonResponse handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return createErrorResponse(ex.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public JsonResponse handleUserNotFoundException(UserNotFoundException ex) {
        return createErrorResponse(ex.getMessage());
    }

    private JsonResponse createErrorResponse(String message) {
        JsonResponse errorResponse = new JsonResponse();
        errorResponse.setField(ERROR_MESSAGE, message);
        return errorResponse;
    }
}
