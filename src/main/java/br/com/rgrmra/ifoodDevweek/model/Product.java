package br.com.rgrmra.ifoodDevweek.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProductId")
    @SequenceGenerator(name = "ProductId", sequenceName = "PRODUCT_ID")
    private long id;
    private String name;
    private BigDecimal price;
    @Builder.Default
    private boolean available = true;
    @Embedded
    @OneToOne
    @JsonIgnore
    private Restaurant restaurant;
}
