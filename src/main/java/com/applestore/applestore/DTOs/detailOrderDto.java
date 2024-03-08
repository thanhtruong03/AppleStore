package com.applestore.applestore.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class detailOrderDto {
    private int order_id, price;
    private String product_name, product_color, customer_l_name, customer_f_name, address_line, city, country, order_date, phone;
}
