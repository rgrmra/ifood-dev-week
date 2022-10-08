package br.com.rgrmra.ifoodDevweek.resorce.dto;

import lombok.*;

import javax.persistence.Embeddable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class ItemDto {
    private long produtoId;
    private int quantidade;
}
