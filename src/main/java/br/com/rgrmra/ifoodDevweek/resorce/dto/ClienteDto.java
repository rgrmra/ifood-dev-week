package br.com.rgrmra.ifoodDevweek.resorce.dto;

import br.com.rgrmra.ifoodDevweek.model.Endereco;
import lombok.*;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ClienteDto {
    private String nome;
    private Endereco endereco;
}
