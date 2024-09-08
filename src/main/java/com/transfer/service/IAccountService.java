package com.transfer.service;

import com.transfer.dto.AccountDTO;
import com.transfer.dto.CreateAccountDTO;
import com.transfer.dto.UpdateAccountDTO;
import com.transfer.exception.custom.ResourceNotFoundException;

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

    AccountDTO updateAccount(Long accountId, UpdateAccountDTO accountDTO);

    void deleteAccount(Long accountId);

    void deposit(Long accountId, Double amount);


}
