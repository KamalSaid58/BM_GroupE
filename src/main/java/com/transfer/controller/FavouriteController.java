package com.transfer.controller;

import com.transfer.entity.Favourite;
import com.transfer.service.IFavouriteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
@Tag(name = "Favourite Controller", description = "Favourite controller")
public class FavouriteController {

    private final IFavouriteService favouriteService;

}
