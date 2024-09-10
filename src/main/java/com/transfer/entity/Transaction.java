package com.transfer.entity;

import com.transfer.dto.TransactionDTOResponse;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_Id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account sourceAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account destinationAccount;


   public TransactionDTOResponse toDTO(){
        return TransactionDTOResponse.builder()
                .sourceAccountNumber(this.sourceAccount.getAccountNumber())
                .destinationAccountNumber(this.destinationAccount.getAccountNumber())
                .amount(this.amount)
                .transactionDate(this.createdAt)
                .build();
    }

}
