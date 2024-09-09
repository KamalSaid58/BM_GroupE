package com.transfer.dto;

import com.transfer.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDTO {

    private Long transactionId;

    private double amount;

    private LocalDateTime createdAt;

    private Account sourceAccount;

    private Account destinationAccount;



}
