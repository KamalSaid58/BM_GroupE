package com.transfer.service;


import com.transfer.dto.AccountDTO;
import com.transfer.dto.CreateAccountDTO;
import com.transfer.dto.GetBalanceDTO;
import com.transfer.dto.GetTransactionDTO;
import com.transfer.entity.Account;
import com.transfer.entity.Customer;
import com.transfer.entity.Transaction;
import com.transfer.exception.custom.ResourceNotFoundException;
import com.transfer.repository.AccountRepository;
import com.transfer.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.SecureRandom;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public Double getBalanceByAccountNumber(GetBalanceDTO getBalanceDTO) throws ResourceNotFoundException {
        String accountNumber = getBalanceDTO.getAccountNumber();

        Account account = (Account) this.accountRepository.findByAccountNumber(accountNumber).orElseThrow(()
                -> new ResourceNotFoundException("Account not found"));
        return account.getBalance();
    }

    @Override
    public Set<Transaction> getTransactionsByAccountNumber(@RequestBody GetTransactionDTO getTransactionDTO) throws ResourceNotFoundException {
        String accountNumber = getTransactionDTO.getAccountNumber();
        Account account = this.accountRepository.findByAccountNumber(accountNumber).orElseThrow(()
                -> new ResourceNotFoundException("Account not found"));

        Set<Transaction> inTransactions = account.getInTransactions();

        Set<Transaction> outTransactions = account.getOutTransactions();

       return Stream.concat(inTransactions.stream(), outTransactions.stream())
                .collect(Collectors.toSet());

    }

    public Set<Transaction> getOutTransactionsById(Long accountId) throws ResourceNotFoundException {
        Account account = this.accountRepository.findById(accountId).orElseThrow(()
                -> new ResourceNotFoundException("Account not found"));
        return account.getOutTransactions();
    }
}
