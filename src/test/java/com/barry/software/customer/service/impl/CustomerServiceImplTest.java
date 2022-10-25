package com.barry.software.customer.service.impl;

import com.barry.software.customer.exceptions.NotValidPhoneNumberException;
import com.barry.software.customer.exceptions.PhoneNumberTakenException;
import com.barry.software.customer.mappers.CustomerMapper;
import com.barry.software.customer.mappers.CustomerMapperImpl;
import com.barry.software.customer.model.Customer;
import com.barry.software.customer.repositories.CustomerRepository;
import com.barry.software.customer.utils.PhoneNumberValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.Mock;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PhoneNumberValidator phoneNumberValidator;
    @Spy
    private CustomerMapper customerMapper = new CustomerMapperImpl();
    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;

    @Test
    void shouldSaveANewCustomer() {
        //given
        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .name("John")
                .phoneNumber("000099")
                .build();

        //when
        given(phoneNumberValidator.isValid(customer.getPhoneNumber())).willReturn(true);
        given(customerRepository.findByPhoneNumber(customer.getPhoneNumber())).willReturn(Optional.empty());

        customerService.saveCustomer(customerMapper.customerToCustomerDto(customer));

        //then
        verify(customerRepository).findByPhoneNumber(anyString());
        verify(phoneNumberValidator).isValid(customer.getPhoneNumber());
        verify(customerRepository,times(1)).save(customerArgumentCaptor.capture());
        assertAll(
                ()-> assertThat(customerArgumentCaptor.getValue()).isNotNull(),
                ()-> assertThat(customerArgumentCaptor.getValue()).isEqualTo(customer)
        );
    }


    @Test
    void shouldSaveANewCustomerWhenIdIsNull() {
        //given
        Customer customer = Customer.builder()
                .id(null)
                .name("John")
                .phoneNumber("000099")
                .build();

        //when
        given(phoneNumberValidator.isValid(customer.getPhoneNumber())).willReturn(true);
        given(customerRepository.findByPhoneNumber(customer.getPhoneNumber())).willReturn(Optional.empty());

        customerService.saveCustomer(customerMapper.customerToCustomerDto(customer));

        //then
        verify(customerRepository).findByPhoneNumber(anyString());
        verify(phoneNumberValidator).isValid(customer.getPhoneNumber());
        verify(customerRepository,times(1)).save(customerArgumentCaptor.capture());
        assertAll(
                ()-> assertThat(customerArgumentCaptor.getValue()).isNotNull(),
                ()-> assertThat(customerArgumentCaptor.getValue().getName()).isEqualTo(customer.getName()),
                ()-> assertThat(customerArgumentCaptor.getValue().getPhoneNumber()).isEqualTo(customer.getPhoneNumber())
        );
    }



    @Test
    void shouldThrowExceptionWhenNewCustomerPhoneNumberIsNotValid() {
        //given
        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .name("John")
                .phoneNumber("000099")
                .build();

        //when
        given(phoneNumberValidator.isValid(customer.getPhoneNumber())).willReturn(false);

        assertThatThrownBy(()->
                customerService.saveCustomer(customerMapper.customerToCustomerDto(customer)))
                .isInstanceOf(NotValidPhoneNumberException.class)
                .hasMessageContaining(String.format("phone number: %s is not valid", customer.getPhoneNumber()));

        //then
        verify(phoneNumberValidator, times(1)).isValid(customer.getPhoneNumber());
        verify(customerRepository,never()).save(any());
    }

    @Test
    void shouldNotSaveCustomerWhenCustomerAlreadyExists() {
        //given
        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .name("John")
                .phoneNumber("000099")
                .build();

        //when
        given(phoneNumberValidator.isValid(customer.getPhoneNumber())).willReturn(true);
        given(customerRepository.findByPhoneNumber(customer.getPhoneNumber())).willReturn(Optional.of(customer));

        customerService.saveCustomer(customerMapper.customerToCustomerDto(customer));

        //then
        verify(phoneNumberValidator, times(1)).isValid(customer.getPhoneNumber());
        verify(customerRepository, times(1)).findByPhoneNumber(customer.getPhoneNumber());
        verify(customerRepository,never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenNewCustomerPhoneNumberIsTaken() {
        //given
        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .name("John")
                .phoneNumber("000099")
                .build();

        Customer customerTwo = Customer.builder()
                .id(UUID.randomUUID())
                .name("Aisha")
                .phoneNumber("000099")
                .build();

        //when
        given(phoneNumberValidator.isValid(customer.getPhoneNumber())).willReturn(true);
        given(customerRepository.findByPhoneNumber(customer.getPhoneNumber())).willReturn(Optional.of(customerTwo));

        assertThatThrownBy(() ->customerService.saveCustomer(customerMapper.customerToCustomerDto(customer)))
                .isInstanceOf(PhoneNumberTakenException.class)
                        .hasMessageContaining(String.format("Phone number: %s is taken", customer.getPhoneNumber()));

        //then
        verify(phoneNumberValidator, times(1)).isValid(customer.getPhoneNumber());
        verify(customerRepository, times(1)).findByPhoneNumber(customer.getPhoneNumber());
        verify(customerRepository,never()).save(any());
    }
}