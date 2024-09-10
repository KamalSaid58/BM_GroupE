package com.transfer.service;

import com.transfer.dto.TransactionDTO;
import com.transfer.entity.Account;
import com.transfer.entity.Transaction;
import com.transfer.repository.AccountRepository;
import com.transfer.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {
    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    @Override
    public void transferMoney(@RequestBody TransactionDTO transactionDTO) {
        Long sourceAccountId = transactionDTO.getSourceAccountId();

        Long destinationAccountId = transactionDTO.getDestinationAccountId();

        double amount = transactionDTO.getAmount();

        Account sourceAccount = accountRepository.findById(sourceAccountId).orElseThrow(() -> new RuntimeException("Source account not found"));

        Account destinationAccount = accountRepository.findById(destinationAccountId).orElseThrow(() -> new RuntimeException("Destination account not found"));

        if (sourceAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        Transaction transaction = Transaction.builder()
                .amount(amount)
                .sourceAccount(sourceAccount)
                .destinationAccount(destinationAccount)
                .build();

        sourceAccount.getInTransactions().add(transaction);

        destinationAccount.getOutTransactions().add(transaction);

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);

        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        transactionRepository.save(transaction);

        accountRepository.save(sourceAccount);

        accountRepository.save(destinationAccount);

    }
}
