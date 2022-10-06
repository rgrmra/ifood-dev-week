package br.com.rgrmra.ifoodDevweek.resorce.dto;

import br.com.rgrmra.ifoodDevweek.model.Restaurante;
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
public class ProdutoDto {
    private String nome;
    private double valorUnitario;
    private boolean disponivel;
    @Embedded
    @ManyToOne
    private Long restauranteId;
}
