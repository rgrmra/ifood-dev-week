package br.com.rgrmra.ifoodDevweek.model;

import lombok.*;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Endereco {
    private String cep;
    private String complemento;
}
