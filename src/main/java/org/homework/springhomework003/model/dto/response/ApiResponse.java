package org.homework.springhomework003.model.dto.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse <T>{
    private String message;
    private HttpStatus status;
    private T payload;
    private boolean success;
    private LocalDateTime timeStamp;

}