package com.applestore.applestore.DTOs;

import lombok.*;
import org.springframework.data.relational.core.sql.In;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDto {
    private String name, description, img, color, price;
    private int product_id, stock;
    public int getIntPrice() {
        String stringPrice = price.replace(".", "").replace(" ", "").replace("₫", "").replace(",", "");
        int intPrice = Integer.parseInt(stringPrice);
        System.out.println(stringPrice + "Check");
        return intPrice;
    }
}
