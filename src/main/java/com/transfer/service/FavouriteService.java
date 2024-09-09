package com.transfer.service;

import com.transfer.repository.FavouriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FavouriteService implements IFavouriteService {
    private final FavouriteRepository favouriteRepository;
}
