package com.barry.software.customer.controller.impl;

import com.barry.software.customer.controller.CustomerController;
import com.barry.software.customer.dtos.CustomerDto;
import com.barry.software.customer.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class CustomerControllerImpl implements CustomerController {

    private final CustomerService customerService;


    @Override
    public void saveCustomer(CustomerDto customerDto) {
        customerService.saveCustomer(customerDto);
    }
}
