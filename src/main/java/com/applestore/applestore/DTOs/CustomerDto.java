package com.applestore.applestore.DTOs;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDto {
    private int user_id, customer_id;
    private String address_line, country, city, phone;
}
