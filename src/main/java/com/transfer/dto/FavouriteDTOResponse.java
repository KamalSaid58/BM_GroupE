package com.transfer.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouriteDTOResponse {
    private Long favourite_Id;

    private String favourite_name;

    private Long customer_Id;






}
