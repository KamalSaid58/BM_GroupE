package com.transfer.controller;

import com.transfer.service.IFavouriteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/favourite")
@RequiredArgsConstructor
@Validated
@Tag(name = "Favourite Controller", description = "Favourite controller")
public class FavouriteController {
private final IFavouriteService favouriteService;


}
