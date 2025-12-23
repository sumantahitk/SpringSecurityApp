package com.SpringSecurityApp.SpringSecurityApp.advices;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiErr {
    private HttpStatus status;
    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime timestamp;
    private String message;

    public ApiErr() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiErr(String message, HttpStatus status) {
        this();
        this.message = message;
        this.status = status;
    }
}
