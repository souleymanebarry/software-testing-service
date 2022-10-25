package com.barry.software.customer.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto {

    private UUID id;
    private String name;
    private String phoneNumber;
}
