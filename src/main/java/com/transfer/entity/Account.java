package com.transfer.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.transfer.dto.AccountDTO;
import com.transfer.dto.enums.AccountCurrency;
import com.transfer.dto.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_Id;

    @Column(nullable = false, unique = true)
    private String accountNumber;

    @Column(nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Double balance;

    private String accountName;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @ManyToOne(fetch = FetchType.EAGER)
    private Customer customer;

    @OneToMany(mappedBy = "sourceAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Transaction> inTransactions = new HashSet<>();

    @OneToMany(mappedBy = "destinationAccount", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Transaction> outTransactions = new HashSet<>();


    public AccountDTO toDTO() {
        return AccountDTO.builder()
                .id(this.account_Id)
                .accountNumber(this.accountNumber)
                .balance(this.balance)
                .accountName(this.accountName)
                .active(this.active)
                .build();
    }

}
