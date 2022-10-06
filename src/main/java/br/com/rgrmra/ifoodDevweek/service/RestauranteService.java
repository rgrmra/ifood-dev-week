package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.model.Endereco;
import br.com.rgrmra.ifoodDevweek.model.Produto;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.resorce.dto.RestauranteDto;

import java.util.List;

public interface RestauranteService {

    Restaurante adicionarRestaurante(RestauranteDto restauranteDto);
    Restaurante verRestaurante(Long id);
    String verNomeRestaurante(Long id);
    Endereco verEnderecoRestaurante(Long id);
    Restaurante atualizarRestaurante(Long id, RestauranteDto restauranteDto);
    Restaurante atualizarNomeRestaurante(Long id, String nome);
    Restaurante atualizarEnderecoRestaurante(Long id, Endereco endereco);
    List<Produto> verListaDeProdutos(Long id);
    void deletarRestaurante(Long id);

}
