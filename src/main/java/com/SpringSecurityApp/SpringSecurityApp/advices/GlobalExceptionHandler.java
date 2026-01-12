package com.SpringSecurityApp.SpringSecurityApp.advices;


import com.SpringSecurityApp.SpringSecurityApp.exception.ResourceNotFoundException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<APIResponse<?>> handleInputValidationErrors(
//            MethodArgumentNotValidException exception) {
//
//        List<String> errors = exception
//                .getBindingResult()
//                .getAllErrors()
//                .stream()
//                .map(error -> error.getDefaultMessage())
//                .collect(Collectors.toList());
//
//        APIError apiError = APIError.builder()
//                .status(HttpStatus.BAD_REQUEST)
//                .message("Validation Failed")
//                .errors(errors)
//                .build();
//
//        return builderErrorResponseEntity(apiError);
//    }
//
//    private ResponseEntity<APIResponse<?>> builderErrorResponseEntity(APIError apiError) {
//        return new ResponseEntity<>(new APIResponse<>(apiError),apiError.getStatus());
//    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErr> handleResourceNotFoundException(ResourceNotFoundException exception){
        ApiErr apiErr = new ApiErr(exception.getLocalizedMessage(),HttpStatus.NOT_FOUND);
//        System.out.println("not found");
        return new ResponseEntity<>(apiErr,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErr> handleValidationException(
            MethodArgumentNotValidException ex) {
        // Extract only the default messages from field errors
        String errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage()) // Only the user-friendly message
                .collect(Collectors.joining(", "));    // Join multiple errors if any


        ApiErr apiErr = new ApiErr(errors, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiErr, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiErr> handleAuthenticationException(AuthenticationException ex){
        ApiErr apiErr= new ApiErr(ex.getLocalizedMessage(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiErr,HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ApiErr> handleJwtException(JwtException ex)
    {
        ApiErr apiErr= new ApiErr(ex.getLocalizedMessage(),HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(apiErr,HttpStatus.UNAUTHORIZED);

    }
}
