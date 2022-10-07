package br.com.rgrmra.ifoodDevweek.model;

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
@Embeddable
@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RestauranteId")
    @SequenceGenerator(name = "RestauranteId", sequenceName = "RESTAURANTE_ID")
    private long id;
    private String nome;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Produto> produtos;
    @Embedded
    private Endereco endereco;

}
