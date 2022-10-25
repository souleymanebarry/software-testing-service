package com.barry.software.payement.services.impl;

import com.barry.software.customer.repositories.CustomerRepository;
import com.barry.software.payement.dtos.CardPaymentChargeDto;
import com.barry.software.payement.dtos.PaymentDto;
import com.barry.software.payement.enums.Currency;
import com.barry.software.payement.repositories.PaymentRepository;
import com.barry.software.payement.services.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

import static com.barry.software.payement.enums.Currency.USD;
import static com.barry.software.payement.enums.Currency.GBP;


@Service
@AllArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    private static final List<Currency> ACCEPTED_CURRENCIES = List.of(USD, GBP);

    private final CustomerRepository customerRepository;
    private final PaymentRepository paymentRepository;
    private final CardPaymentChargeDto cardPaymentChargeDto;

    @Override
    public void chargeCard(UUID customerId, PaymentDto paymentDto) {

    }
}
