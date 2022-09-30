package br.com.rgrmra.ifoodDevweek.model;

import br.com.rgrmra.ifoodDevweek.enumeration.FormaPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Sacola {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> itens;
    private Double valorTotal;
    @Enumerated
    private FormaPagamento formaPagamento;
    private boolean fechada;
}
