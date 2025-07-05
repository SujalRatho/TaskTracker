package com.Spring.Tasks.controllers;

import com.Spring.Tasks.domain.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice  // tells spring that this class handles exception across all of our controllers
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleException(
            RuntimeException ex, WebRequest request
            ){
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(), request.getDescription(false));

        // spring going to convert error response to JSON and send that to client
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }
}
