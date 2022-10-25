package com.barry.software.customer.utils;


import org.springframework.stereotype.Service;

@Service
public class PhoneNumberValidator {

    public boolean isValid(String phoneNumber) {
        return phoneNumber.startsWith("+44") && phoneNumber.length() ==13;
    }
}
