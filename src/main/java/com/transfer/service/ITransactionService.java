package com.transfer.service;

import com.transfer.dto.TransactionDTOResponse;
import com.transfer.dto.TransferDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface ITransactionService {

     void transferMoney(@RequestBody TransferDTO transferDTO) ;



}
