package br.com.rgrmra.ifoodDevweek.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Embeddable
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RestaurantId")
    @SequenceGenerator(name = "RestaurantId", sequenceName = "RESTAURANT_ID")
    private long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;
    @Embedded
    private Address address;

}
