package com.transfer.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginCustomer {
    private String email;

    private String password;

    private String token;
}
