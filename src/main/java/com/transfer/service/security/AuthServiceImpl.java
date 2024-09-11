package com.transfer.service.security;


import com.transfer.dto.LoginRequestDTO;
import com.transfer.dto.LoginResponseDTO;
import com.transfer.dto.RegisterCustomerRequest;
import com.transfer.dto.RegisterCustomerResponse;
import com.transfer.dto.enums.AccountCurrency;
import com.transfer.dto.enums.AccountType;
import com.transfer.entity.Account;
import com.transfer.entity.Customer;
import com.transfer.exception.custom.CustomerAlreadyExistException;
import com.transfer.exception.custom.ResourceNotFoundException;
import com.transfer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestHeader;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;

    private final TokenStore tokenStore;


    @Transactional
    public RegisterCustomerResponse register(RegisterCustomerRequest customerRequest) throws CustomerAlreadyExistException {

        if (Boolean.TRUE.equals(this.customerRepository.existsByEmail(customerRequest.getEmail()))) {
            throw new CustomerAlreadyExistException("Customer with email " + customerRequest.getEmail() + " already exists");
        }

        Customer customer = Customer.builder()
                .email(customerRequest.getEmail())
                .password(this.passwordEncoder.encode(customerRequest.getPassword()))
                .name(customerRequest.getName())
                .build();

        Account account = Account.builder()
                .balance(customerRequest.getBalance())
                .accountType(customerRequest.getAccountType())
                .accountName(customerRequest.getAccountName())
                .currency(customerRequest.getCurrency())
                .accountNumber(new SecureRandom().nextInt(1000000000) + "")
                .customer(customer)
                .build();

        customer.getAccounts().add(account);

        Customer savedCustomer = customerRepository.save(customer);


        return savedCustomer.toResponse();
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        return LoginResponseDTO.builder()
                .token(jwt)
                .message("Login Successful")
                .status(HttpStatus.ACCEPTED)
                .tokenType("Bearer")
                .build();
    }

    @Override
    public ResponseEntity<String> logout(String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            try {
                tokenStore.invalidateToken(token);
                SecurityContextHolder.clearContext(); // Ensure context is cleared
                return ResponseEntity.ok("Logged out successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Token invalidation failed");
            }
        }
        return ResponseEntity.badRequest().body("Invalid token");
    }
}
