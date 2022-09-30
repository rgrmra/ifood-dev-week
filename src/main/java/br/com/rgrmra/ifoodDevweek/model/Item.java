package br.com.rgrmra.ifoodDevweek.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne
    private Produto produto;
    private int quantidade;
    @ManyToOne
    @JsonIgnore
    private Sacola sacola;


    public static void main(String[] args) {
        int[] a = {1,2};
        for (int i = 0 ; i < a.length; i++) {

        }
    }
}
