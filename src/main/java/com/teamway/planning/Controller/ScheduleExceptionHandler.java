package com.teamway.planning.Controller;

import com.teamway.planning.exception.ErrorResponse;
import com.teamway.planning.exception.SchedulerException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
@RequiredArgsConstructor
public class ScheduleExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SchedulerException.class)
    public ResponseEntity<Object> handleScheduleException(final SchedulerException schedulerException, final WebRequest request) {
        var errorResponse = ErrorResponse.builder()
                .date(ZonedDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .message(schedulerException.getMessage())
                .path(request != null ? ((ServletWebRequest)request).getRequest().getRequestURI():null)
                .build();
        return handleExceptionInternal(schedulerException, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
