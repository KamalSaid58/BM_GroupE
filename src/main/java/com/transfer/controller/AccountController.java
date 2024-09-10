package com.transfer.controller;

import com.transfer.dto.*;
import com.transfer.exception.custom.ResourceNotFoundException;
import com.transfer.exception.response.ErrorDetails;
import com.transfer.service.IAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
@Tag(name = "Account Controller", description = "Account controller")
public class AccountController {

    private final IAccountService accountService;

    @Operation(summary = "Create new Account")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AccountDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @PostMapping("createAccount")
    public AccountDTO createAccount(@Valid @RequestBody CreateAccountDTO accountDTO) throws ResourceNotFoundException {
        return this.accountService.createAccount(accountDTO);
    }

    @Operation(summary = "Get Account by Id")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = AccountDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @GetMapping("/getAccount/{accountId}")
    public AccountDTO getAccountById(@PathVariable Long accountId) throws ResourceNotFoundException {
        return this.accountService.getAccountById(accountId);
    }

    @Operation(summary = "Get Balance by Account Number")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Double.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @GetMapping("/balance")
    public Double getBalanceByAccountNumber(@RequestBody GetBalanceDTO getBalanceDTO) throws ResourceNotFoundException {
        return this.accountService.getBalanceByAccountNumber(getBalanceDTO);
    }

    @Operation(summary = "Get transaction history by account number")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Set.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @GetMapping("/transactions")
    public Set<TransactionDTOResponse> getTransactionsByAccountNumber(@RequestBody GetTransactionDTO getTransactionDTO) throws ResourceNotFoundException {
        return this.accountService.getTransactionsByAccountNumber(getTransactionDTO);
    }






}
