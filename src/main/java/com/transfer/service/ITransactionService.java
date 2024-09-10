package com.transfer.service;

import com.transfer.dto.TransactionDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface ITransactionService {

     void transferMoney(@RequestBody TransactionDTO transactionDTO) ;



}
