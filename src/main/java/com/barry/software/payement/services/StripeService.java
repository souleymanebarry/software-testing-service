package com.barry.software.payement.services;

import com.barry.software.payement.dtos.CardPaymentChargeDto;
import com.barry.software.payement.enums.Currency;
import com.barry.software.payement.stripe.StripeApi;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.net.RequestOptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Service
@AllArgsConstructor
@Slf4j
public class StripeService implements CardPaymentCharger {

    private final StripeApi stripeApi;

    private final RequestOptions requestOptions = RequestOptions.builder()
            .setApiKey("sk_test_4eC39HqLyjWDarjtT1zdp7dc")
            .build();

    @Override
    public CardPaymentChargeDto chargeCard(String cardSource, BigDecimal amount, Currency currency, String description){
        Map<String, Object> params = new HashMap<>();
        params.put("amount",amount);
        params.put("currency",currency);
        params.put("source",cardSource);
        params.put("description",description);

        try {
            Charge charge = stripeApi.create(params, requestOptions);
            return new CardPaymentChargeDto(charge.getPaid());
        }catch (StripeException e){
            throw new IllegalStateException("annot make stripe charge",e);
        }
    }
}
