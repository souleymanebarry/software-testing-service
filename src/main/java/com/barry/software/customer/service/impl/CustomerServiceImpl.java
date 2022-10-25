package com.barry.software.customer.service.impl;

import com.barry.software.customer.dtos.CustomerDto;
import com.barry.software.customer.exceptions.NotValidPhoneNumberException;
import com.barry.software.customer.exceptions.PhoneNumberTakenException;
import com.barry.software.customer.mappers.CustomerMapper;
import com.barry.software.customer.model.Customer;
import com.barry.software.customer.repositories.CustomerRepository;
import com.barry.software.customer.service.CustomerService;
import com.barry.software.customer.utils.PhoneNumberValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final PhoneNumberValidator phoneNumberValidator;
    private final CustomerMapper customerMapper;

    @Override
    public void saveCustomer(CustomerDto customerDto) {
        String phoneNumber = customerDto.getPhoneNumber();
        if(!phoneNumberValidator.isValid(phoneNumber))
            throw new NotValidPhoneNumberException(String.format("phone number: %s is not valid", phoneNumber));
        Optional<Customer> customerOptional = customerRepository.findByPhoneNumber(phoneNumber);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            if (customer.getName().equals(customerDto.getName())){
               return;
            }
            throw new PhoneNumberTakenException(String.format("Phone number: %s is taken", phoneNumber));
        }

        if(customerDto.getId()==null)
            customerDto.setId(UUID.randomUUID());
        Customer customerSaved = customerMapper.customerDtoToCustomer(customerDto);
        customerRepository.save(customerSaved);
    }
}
