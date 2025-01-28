package br.com.hadryan.ecommerce.product.model;

import lombok.Getter;

@Getter
public enum Category {

    CLOTHING(1, "Clothing", "Clothing category"),
    ELECTRONICS(2, "Electronics", "Electronics category"),
    BOOKS(3, "Books", "Books category");

    private final int id;
    private final String name;
    private final String description;

    Category(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

}
