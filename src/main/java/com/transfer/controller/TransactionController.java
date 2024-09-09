package com.transfer.controller;

import com.transfer.dto.AccountDTO;
import com.transfer.dto.TransactionDTO;
import com.transfer.exception.response.ErrorDetails;
import com.transfer.service.ITransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Transaction")
@RequiredArgsConstructor
@Validated
@Tag(name = "Transaction Controller", description = "Transaction controller")
public class TransactionController {
    private final ITransactionService transactionService;



}
