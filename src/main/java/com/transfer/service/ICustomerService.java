package com.transfer.service;

import com.transfer.dto.AddFavDTO;
import com.transfer.dto.CustomerDTO;
import com.transfer.dto.GetFavDTO;
import com.transfer.entity.Favourite;
import com.transfer.exception.custom.ResourceNotFoundException;

import java.util.Set;

public interface ICustomerService {

    /**
     * Get customer by id
     *
     * @param customerId the customer id
     * @return the created customer
     * @throws ResourceNotFoundException if the customer is not found
     */
    CustomerDTO getCustomerById(Long customerId) throws ResourceNotFoundException;

    void addFavourite (AddFavDTO addFavDTO) throws ResourceNotFoundException;

    Set<Favourite> getFavourites(GetFavDTO getFavDTO) throws ResourceNotFoundException;

    void deleteFavourite(Long id) throws ResourceNotFoundException;
}
