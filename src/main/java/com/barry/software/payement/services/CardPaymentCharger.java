package com.barry.software.payement.services;

import com.barry.software.payement.dtos.CardPaymentChargeDto;
import com.barry.software.payement.enums.Currency;

import java.math.BigDecimal;

public interface CardPaymentCharger {

    CardPaymentChargeDto chargeCard(String cardSource, BigDecimal amount, Currency currency, String description);

}
