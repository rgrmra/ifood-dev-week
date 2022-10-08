package br.com.rgrmra.ifoodDevweek.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProdutId")
    @SequenceGenerator(name = "ProdutId", sequenceName = "PRODUT_ID")
    private long id;
    private String name;
    private double price;
    @Builder.Default
    private boolean available = true;
    @Embedded
    @OneToOne
    @JsonIgnore
    private Restaurant restaurant;
}
