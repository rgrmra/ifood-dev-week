package br.com.rgrmra.ifoodDevweek.model;

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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ClientId")
    @SequenceGenerator(name = "ClientId", sequenceName = "CLIENT_ID")
    private long id;
    private String name;
    @Embedded
    private Address address;
}
