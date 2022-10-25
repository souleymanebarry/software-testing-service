package com.barry.software.payement.dtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class CardPaymentChargeDto {

    private boolean isCardDebited;
}
