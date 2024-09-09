package com.transfer.entity;

import com.transfer.dto.TransactionDTO;
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
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;


    public TransactionDTO toDTO() {
        return TransactionDTO.builder()
                .transactionId(this.transaction_Id)
                .amount(this.amount)
                .createdAt(this.createdAt)
                .sourceAccount(this.sourceAccount)
                .destinationAccount(this.destinationAccount)
                .build();
    }

}
