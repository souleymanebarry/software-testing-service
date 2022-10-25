package com.barry.software.payement.dtos;

import com.barry.software.payement.enums.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class PaymentDto {

    private Long id;
    private UUID customerId;
    private BigDecimal amount;
    private Currency currency;
    private String source;
    private String description;
}
