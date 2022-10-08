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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ItemId")
    @SequenceGenerator(name = "ItemId", sequenceName = "ITEM_ID")
    private long id;
    private ItemProduct itemProduct;
    private int quantity;
    @ManyToOne
    @JsonIgnore
    private Cart cart;
}
