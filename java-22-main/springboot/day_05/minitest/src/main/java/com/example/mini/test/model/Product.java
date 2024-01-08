package com.example.mini.test.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    int id;
    String name;
    String description;
    String thumbnail;
    int prince;
    double rating;
    int priceDiscount;

    public Product(int id, String name, String description, String thumbnail, int prince, double rating, int priceDiscount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.prince = prince;
        this.rating = rating;
        this.priceDiscount = priceDiscount;
    }
}
