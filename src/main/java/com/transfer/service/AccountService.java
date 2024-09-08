package com.transfer.service;


import com.transfer.dto.AccountDTO;
import com.transfer.dto.CreateAccountDTO;
import com.transfer.dto.UpdateAccountDTO;
import com.transfer.entity.Account;
import com.transfer.entity.Customer;
import com.transfer.exception.custom.ResourceNotFoundException;
import com.transfer.repository.AccountRepository;
import com.transfer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final AccountRepository accountRepository;

    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public AccountDTO createAccount(CreateAccountDTO accountDTO) throws ResourceNotFoundException {

        Customer customer = this.customerRepository.findById(accountDTO.getCustomerId()).orElseThrow(()
                -> new ResourceNotFoundException("Customer with id " + accountDTO.getCustomerId() + " not found"));

        Account account = Account.builder()
                .accountNumber(new SecureRandom().nextInt(1000000000) + "")
                .accountType(accountDTO.getAccountType())
                .accountName(accountDTO.getAccountName())
                .accountDescription(accountDTO.getAccountDescription())
                .currency(accountDTO.getCurrency())
                .balance(0.0)
                .customer(customer)
                .build();

        Account savedAccount = this.accountRepository.save(account);

        return savedAccount.toDTO();
    }

    @Override
    public AccountDTO getAccountById(Long accountId) throws ResourceNotFoundException {
        return this.accountRepository.findById(accountId).orElseThrow(()
                -> new ResourceNotFoundException("Account not found")).toDTO();
    }

    @Override
    public AccountDTO updateAccount(Long accountId, UpdateAccountDTO accountDTO) {
        return null;
    }

    @Override
    public void deleteAccount(Long accountId) {

    }

    @Override
    public void deposit(Long accountId, Double amount) {

    }
}
