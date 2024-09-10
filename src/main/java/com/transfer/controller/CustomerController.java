package com.transfer.controller;


import com.transfer.dto.AddFavDTO;
import com.transfer.dto.CustomerDTO;
import com.transfer.dto.FavouriteDTOResponse;
import com.transfer.dto.GetFavDTO;
import com.transfer.entity.Favourite;
import com.transfer.exception.custom.ResourceNotFoundException;
import com.transfer.exception.response.ErrorDetails;
import com.transfer.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
@Tag(name = "Customer Controller", description = "Customer controller")
public class CustomerController {

    private final ICustomerService customerService;

    @Operation(summary = "Get customer by id")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = CustomerDTO.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @GetMapping("/{customerId}")
    public CustomerDTO getCustomerById(@PathVariable Long customerId) throws ResourceNotFoundException {
        return this.customerService.getCustomerById(customerId);
    }

    @Operation(summary = "Add favourite to customer")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = FavouriteDTOResponse.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @PostMapping("/favorites")
    public FavouriteDTOResponse addFavourite(@RequestBody AddFavDTO addFavDTO) throws ResourceNotFoundException {
         return this.customerService.addFavourite(addFavDTO);
    }

    @Operation(summary = "Get favourites to customer")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Set.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @GetMapping("/favorites")
    public Set<FavouriteDTOResponse> getFavourites(@RequestBody GetFavDTO getFavDTO) throws ResourceNotFoundException {
        return this.customerService.getFavourites(getFavDTO);
    }

    @Operation(summary = "Delete favourite to customer")
    @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Set.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "400", content = {@Content(schema = @Schema(implementation = ErrorDetails.class), mediaType = "application/json")})
    @DeleteMapping("/favorites/{id}")
    public void deleteFavourite(@PathVariable Long id) throws ResourceNotFoundException {
         this.customerService.deleteFavourite(id);
    }








}
