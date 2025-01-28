package br.com.hadryan.ecommerce.product.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    @SequenceGenerator(name = "product_generator", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String description;
    @Min(0)
    private Integer availableQuantity;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private Category category;

}
