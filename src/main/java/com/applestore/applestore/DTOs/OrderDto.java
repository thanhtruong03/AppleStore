package com.applestore.applestore.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int order_id, product_id;
    private String order_date;
}
