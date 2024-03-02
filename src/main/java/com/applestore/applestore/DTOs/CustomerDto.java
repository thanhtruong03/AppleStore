package com.applestore.applestore.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private int user_id;
    private String address_line, country, city, phone;
}
