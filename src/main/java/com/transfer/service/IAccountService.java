package com.transfer.service;

import com.transfer.dto.*;
import com.transfer.exception.custom.ResourceNotFoundException;
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

    Set<TransactionDTOResponse> getTransactionsByAccountNumber(GetTransactionDTO getTransactionDTO) throws ResourceNotFoundException ;



}
