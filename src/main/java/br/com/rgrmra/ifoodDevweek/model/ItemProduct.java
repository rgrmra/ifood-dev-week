package br.com.rgrmra.ifoodDevweek.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ItemProduct {
    private String nome;
    private BigDecimal price;
    private Long restaurantId;
}
