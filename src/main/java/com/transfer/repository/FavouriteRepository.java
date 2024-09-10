package com.transfer.repository;

import com.transfer.entity.Favourite;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FavouriteRepository extends JpaRepository<Favourite, Long> {

    Optional<Favourite> findByFavouriteId(Long favouriteId);
}
