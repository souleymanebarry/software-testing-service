package com.barry.software.customer.repositories;

import com.barry.software.customer.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ExtendWith(SpringExtension.class)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldFindByPhoneNumber() {
        //given
        Customer customer = Customer.builder()
                .id(UUID.randomUUID())
                .name("John")
                .phoneNumber("000099")
                .build();
        customerRepository.save(customer);

        //when
        Optional<Customer> result = customerRepository.findByPhoneNumber(customer.getPhoneNumber());

        //then

        if (result.isPresent()) {
            assertAll(
                    () -> assertEquals(result.get().getPhoneNumber(), customer.getPhoneNumber()),
                    () -> assertThat(result).isPresent()
                            .hasValueSatisfying(c -> assertThat(c).isEqualToComparingFieldByField(customer))
            );
        }
    }

    @Test
    void shouldFindByPhoneNumberWhenDoesNotExists() {
        //given
        String phoneNumber ="000000";
        //when

        Optional<Customer> result = customerRepository.findByPhoneNumber(phoneNumber);

        //then
        assertThat(result).isNotPresent();
    }
}