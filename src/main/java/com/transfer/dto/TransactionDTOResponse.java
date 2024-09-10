package com.transfer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTOResponse {
    private String sourceAccountNumber;

    private String destinationAccountNumber;

    private double amount;

    private LocalDateTime transactionDate;




}
