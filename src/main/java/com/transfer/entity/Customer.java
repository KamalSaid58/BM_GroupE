package com.transfer.entity;

import com.transfer.dto.CustomerDTO;
import com.transfer.dto.RegisterCustomerResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customer_Id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Favourite> favourites = new HashSet<>();


    public RegisterCustomerResponse toResponse() {
        return RegisterCustomerResponse.builder()
                .id(this.customer_Id)
                .name(this.name)
                .email(this.email)
                .build();
    }

    public CustomerDTO toDTO() {
        return CustomerDTO.builder()
                .id(this.customer_Id)
                .name(this.name)
                .email(this.email)
                .accounts(this.accounts.stream().map(Account::toDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

}
