package br.com.rgrmra.ifoodDevweek.resorce.dto;

import br.com.rgrmra.ifoodDevweek.model.Address;
import lombok.*;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class RestaurantDto {
    private String name;
    private Address address;
}
