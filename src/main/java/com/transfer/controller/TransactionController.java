package com.transfer.controller;

import com.transfer.dto.TransactionDTOResponse;
import com.transfer.dto.TransferDTO;
import com.transfer.exception.response.ErrorDetails;
import com.transfer.service.ITransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
@Tag(name = "Transaction Controller", description = "Transaction controller")
public class TransactionController {
    private final ITransactionService transactionService;




    @Operation(summary = "Transfer Money")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = void.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferDTO transferDTO){
        transactionService.transferMoney(transferDTO);
    }



}
