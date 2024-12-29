package com.incode.companyregistryservice.web;

import com.incode.companyregistryservice.api.web.model.ErrorResponse;
import com.incode.companyregistryservice.exception.CustomException;
import com.incode.companyregistryservice.exception.CustomExceptionKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private final Map<CustomExceptionKey, HttpStatus> exceptionKeyHttpCode = Map
            .of(CustomExceptionKey.SERVICE_NOT_AVAILABLE, HttpStatus.SERVICE_UNAVAILABLE);


    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<Object> handleCustomException(CustomException exception) {
        log.info("Custom exception caught:{}.", exception.getMessage());
        HttpStatus exceptionStatus = exceptionKeyHttpCode
                .getOrDefault(exception.getCustomExceptionKey(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(exceptionStatus)
                .body(new ErrorResponse()
                        .errorCode(exceptionStatus.name())
                        .error(exception.getMessage())
                        .timestamp(OffsetDateTime.now())
                        .reasons(exception.getParams()));
    }
}
