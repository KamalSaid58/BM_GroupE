package com.transfer.service;

import com.transfer.dto.*;
import com.transfer.entity.Transaction;
import com.transfer.exception.custom.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Set;

public interface IAccountService {

    /**
     * Create a new account
     *
     * @param accountDTO the account to be created
     * @return the created account
     * @throws ResourceNotFoundException if the account is not found
     */
    AccountDTO createAccount(CreateAccountDTO accountDTO) throws ResourceNotFoundException;

    /**
     * Get account by id
     *
     * @param accountId the account id
     * @return the account
     * @throws ResourceNotFoundException if the account is not found
     */
    AccountDTO getAccountById(Long accountId) throws ResourceNotFoundException;

    Double getBalanceByAccountNumber(GetBalanceDTO getBalanceDTO) throws ResourceNotFoundException;

    Set<Transaction> getTransactionsByAccountNumber(@RequestBody GetTransactionDTO getTransactionDTO) throws ResourceNotFoundException ;

    Set<Transaction> getOutTransactionsById(Long accountId) throws ResourceNotFoundException;

}
