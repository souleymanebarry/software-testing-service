package com.barry.software;


import com.barry.software.customer.dtos.CustomerDto;
import com.barry.software.customer.model.Customer;
import com.barry.software.customer.repositories.CustomerRepository;
import com.barry.software.customer.service.impl.CustomerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class TestingApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestingApplication.class, args);
    }

    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository, CustomerServiceImpl customerService){
        return args -> {
            customerRepository.save(new Customer(UUID.randomUUID(), "John","+440000000000"));
            customerRepository.save(new Customer(UUID.randomUUID(), "Aisha","+44000000313"));
            customerRepository.save(new Customer(UUID.randomUUID(), "Souleymane","+440003130000"));
            customerRepository.save(new Customer(UUID.randomUUID(), "Burn","+44000009790"));
            customerRepository.findAll().forEach(System.out::println);
            Optional<Customer> byPhoneNumber = customerRepository.findByPhoneNumber("+44000000000");

            byPhoneNumber.ifPresent(customer -> log.info("#byPhoneNumber: {} ", customer.getName()));

            CustomerDto customer =new CustomerDto();
            customer.setId(UUID.randomUUID());
            customer.setName("John");
            customer.setPhoneNumber("+440000000");
            customerService.saveCustomer(customer);
        };
    }
}