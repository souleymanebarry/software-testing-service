package com.barry.software.customer.mappers;


import com.barry.software.customer.dtos.CustomerDto;
import com.barry.software.customer.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDto customerDto);

    CustomerDto  customerToCustomerDto(Customer customer);
}
