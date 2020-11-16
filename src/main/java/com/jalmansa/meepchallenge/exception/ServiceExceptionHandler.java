package com.jalmansa.meepchallenge.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ServiceExceptionHandler {

        @ExceptionHandler(UnexpectedApiResponseException.class)
        public void unexpectedApiException(UnexpectedApiResponseException ex) {
            log.error("API call triggered an error with message", ex.getMessage(), ex);
        }

        @ExceptionHandler(Exception.class)
        public void unexpectedException(UnexpectedApiResponseException ex) {
            log.error("API call triggered an error with message", ex.getMessage(), ex);
        }
}
