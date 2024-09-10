package com.transfer.entity;


import com.transfer.dto.FavouriteDTOResponse;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Favourite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long general_Id;

    @Column(nullable = false)
    private Long favouriteId;

    @Column(nullable = false)
    private String favourite_email;

    @Column(nullable = false)
    private String favourite_name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_Id")
    private Customer customer;




    public FavouriteDTOResponse toDTO() {
        return FavouriteDTOResponse.builder()
                .favourite_Id(this.favouriteId)
                .favourite_name(this.favourite_name)
                .customer_Id(this.customer.getCustomer_Id())
                .build();
    }



}
