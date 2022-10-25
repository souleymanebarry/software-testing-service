package com.barry.software.customer.exceptions;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Slf4j
public class NotValidPhoneNumberException extends RuntimeException{

    public NotValidPhoneNumberException(String message) {
        super(message);
        log.error(message);
    }
}
