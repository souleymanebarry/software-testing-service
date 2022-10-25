package com.barry.software.customer.controller;


import com.barry.software.customer.dtos.CustomerDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("api/v1/customers")
public interface CustomerController {

    @PostMapping
     void saveCustomer(CustomerDto customerDto);
}
