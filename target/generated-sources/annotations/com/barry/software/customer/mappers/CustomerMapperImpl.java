package com.barry.software.customer.mappers;

import com.barry.software.customer.dtos.CustomerDto;
import com.barry.software.customer.model.Customer;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-25T00:16:42+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.11 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer customer = new Customer();

        customer.setId( customerDto.getId() );
        customer.setName( customerDto.getName() );
        customer.setPhoneNumber( customerDto.getPhoneNumber() );

        return customer;
    }

    @Override
    public CustomerDto customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId( customer.getId() );
        customerDto.setName( customer.getName() );
        customerDto.setPhoneNumber( customer.getPhoneNumber() );

        return customerDto;
    }
}
