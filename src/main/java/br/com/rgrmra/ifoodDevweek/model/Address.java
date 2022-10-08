package br.com.rgrmra.ifoodDevweek.model;

import lombok.*;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class Address {
    private String zipCode;
    private String address;
    private String secondAddress;
    private int number;
    private String city;
    private String state;
}
