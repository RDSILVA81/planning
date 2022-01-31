package com.teamway.planning.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Builder
@Getter
public class ErrorResponse {
    private ZonedDateTime date;
    private HttpStatus status;
    private String message;
    private String path;
}
