package com.transfer.service;

import com.transfer.dto.TransactionDTOResponse;
import com.transfer.dto.TransferDTO;
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
    public void transferMoney(@RequestBody TransferDTO transferDTO) {
        String sourceAccountNumber = transferDTO.getSourceAccountNumber();

        String destinationAccountNumber = transferDTO.getDestinationAccountNumber();

        double amount = transferDTO.getAmount();

        Account sourceAccount = accountRepository.findByAccountNumber(sourceAccountNumber).orElseThrow(() -> new RuntimeException("Source account not found"));

        Account destinationAccount = accountRepository.findByAccountNumber(destinationAccountNumber).orElseThrow(() -> new RuntimeException("Destination account not found"));

        if (sourceAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        Transaction transaction = Transaction.builder()
                .amount(amount)
                .sourceAccount(sourceAccount)
                .destinationAccount(destinationAccount)
                .build();

        sourceAccount.getOutTransactions().add(transaction);

        destinationAccount.getInTransactions().add(transaction);

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);

        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        transactionRepository.save(transaction);

        accountRepository.save(sourceAccount);

        accountRepository.save(destinationAccount);

    }
}
