package com.barry.software.customer.exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PhoneNumberTakenException extends RuntimeException {
    public PhoneNumberTakenException(String message) {
            super(message);
            log.error(message);
    }
}
