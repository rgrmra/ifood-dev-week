package br.com.rgrmra.ifoodDevweek.model;

import br.com.rgrmra.ifoodDevweek.enumeration.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CartId")
    @SequenceGenerator(name = "CartId", sequenceName = "CART_ID")
    private long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIgnore
    private Client client;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;
    private BigDecimal finalPrice;
    @Enumerated
    private PaymentMethod paymentMethod;
    private boolean closed;
}
