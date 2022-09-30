package br.com.rgrmra.ifoodDevweek.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nome;
    private double valorUnitario;
    @Builder.Default
    private boolean disponivel = true;
    @ManyToOne
    @JsonIgnore
    private Restaurante restaurante;
}
