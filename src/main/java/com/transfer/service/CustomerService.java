package com.transfer.service;

import com.transfer.dto.AddFavDTO;
import com.transfer.dto.CustomerDTO;
import com.transfer.dto.FavouriteDTOResponse;
import com.transfer.dto.GetFavDTO;
import com.transfer.entity.Customer;
import com.transfer.entity.Favourite;
import com.transfer.exception.custom.ResourceNotFoundException;
import com.transfer.repository.CustomerRepository;
import com.transfer.repository.FavouriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

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
    public FavouriteDTOResponse addFavourite(AddFavDTO addFavDTO) throws ResourceNotFoundException {
        String customerEmail = addFavDTO.getCustomerEmail();

        String favouriteEmail = addFavDTO.getFavouriteEmail();

        String favouriteName = this.customerRepository.findUserByEmail(favouriteEmail).get().getName();

        Long favouriteId = this.customerRepository.findUserByEmail(favouriteEmail).get().getCustomer_Id();



        Customer customer= this.customerRepository.findUserByEmail(customerEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        Favourite favourite = Favourite.builder()
                .favouriteId(favouriteId)
                .favourite_name(favouriteName)
                .favourite_email(favouriteEmail)
                .customer(customer)
                .build();


        customer.getFavourites().add(favourite);

        favourite.setCustomer(customer);

        this.customerRepository.save(customer);

        return favourite.toDTO();

    }

    @Override
    public Set<FavouriteDTOResponse> getFavourites(GetFavDTO getFavDTO) throws ResourceNotFoundException {
        String email = getFavDTO.getEmail();
        Customer customer = this.customerRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        return customer.getFavourites().stream().map(Favourite::toDTO).collect(Collectors.toSet());

    }

    @Override
    public void deleteFavourite(Long id) throws ResourceNotFoundException {

        Favourite favourite = this.favouriteRepository.findByFavouriteId(id)
                .orElseThrow(() -> new ResourceNotFoundException("Favourite not found"));

        Customer customer = favourite.getCustomer();

        favourite.setCustomer(null);

        customer.getFavourites().remove(favourite);

        this.customerRepository.save(customer);

        this.favouriteRepository.delete(favourite);
    }
}
