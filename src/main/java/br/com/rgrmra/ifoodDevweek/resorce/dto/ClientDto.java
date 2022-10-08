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
public class ClientDto {
    private String name;
    private Address address;
}
