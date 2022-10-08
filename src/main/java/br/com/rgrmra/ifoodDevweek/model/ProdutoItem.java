package br.com.rgrmra.ifoodDevweek.model;

import lombok.*;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ProdutoItem {
    private String nome;
    private double valorUnitario;
    private Long restauranteId;
}