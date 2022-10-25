package com.barry.software.payement.services;

import com.barry.software.payement.dtos.PaymentDto;

import java.util.UUID;

public interface PaymentService {

    void chargeCard(UUID customerId, PaymentDto paymentDto);
}
