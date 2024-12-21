package com.aphatheology.transactionrestapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequestDto {
    private String transactionId;
    private String debitAccount;
    private String creditAccount;
    private BigDecimal amount;
    private String description;
}
