package com.transfer.service;

import com.transfer.dto.AddFavDTO;
import com.transfer.dto.CustomerDTO;
import com.transfer.dto.GetFavDTO;
import com.transfer.entity.Customer;
import com.transfer.entity.Favourite;
import com.transfer.exception.custom.ResourceNotFoundException;
import com.transfer.repository.CustomerRepository;
import com.transfer.repository.FavouriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    private final FavouriteRepository favouriteRepository;

    @Override
    public CustomerDTO getCustomerById(Long customerId) throws ResourceNotFoundException {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"))
                .toDTO();
    }

    @Override
    public void addFavourite(AddFavDTO addFavDTO) throws ResourceNotFoundException {
        Long customerId = addFavDTO.getCustomerId();

        Long favouriteId = addFavDTO.getFavouriteId();

        Customer customer= this.customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Favourite favourite = this.favouriteRepository.findById(favouriteId)
                .orElseThrow(() -> new ResourceNotFoundException("Favourite not found"));

        customer.getFavourites().add(favourite);

        favourite.setFav_to_customer(customer);

        this.customerRepository.save(customer);

        this.favouriteRepository.save(favourite);

    }

    @Override
    public Set<Favourite> getFavourites(GetFavDTO getFavDTO) throws ResourceNotFoundException {
        String email = getFavDTO.getEmail();
        Customer customer=this.customerRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        return customer.getFavourites();

    }

    @Override
    public void deleteFavourite(Long id) throws ResourceNotFoundException {

        Favourite favourite = this.favouriteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favourite not found"));

        Customer customer = favourite.getFav_to_customer();

        favourite.setFav_to_customer(null);

        customer.getFavourites().remove(favourite);

        this.customerRepository.save(customer);

        this.favouriteRepository.save(favourite);
    }
}
