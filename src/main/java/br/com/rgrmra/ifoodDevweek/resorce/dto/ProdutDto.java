package br.com.rgrmra.ifoodDevweek.resorce.dto;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.ManyToOne;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ProdutDto {
    private String name;
    private double price;
    private boolean available;
    @Embedded
    @ManyToOne
    private Long restaurantId;
}
