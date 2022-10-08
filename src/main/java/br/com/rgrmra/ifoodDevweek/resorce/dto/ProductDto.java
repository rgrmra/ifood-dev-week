package br.com.rgrmra.ifoodDevweek.resorce.dto;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ProductDto {
    private String name;
    private BigDecimal price;
    private boolean available;
    @Embedded
    @ManyToOne
    private Long restaurantId;
}
