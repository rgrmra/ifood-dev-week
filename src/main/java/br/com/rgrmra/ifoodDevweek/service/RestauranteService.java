package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.model.Endereco;
import br.com.rgrmra.ifoodDevweek.model.Produto;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.resorce.dto.RestauranteDto;

import java.util.List;

public interface RestauranteService {

    List<Restaurante> verRestaurantes();
    Restaurante adicionarRestaurante(RestauranteDto restauranteDto);
    Restaurante verRestaurante(Long id);
    String verNomeRestaurante(Long id);
    Endereco verEnderecoRestaurante(Long id);
    List<Restaurante> pesquisarRestaurante(String nome);
    Restaurante atualizarRestaurante(Long id, RestauranteDto restauranteDto);
    Restaurante atualizarNomeRestaurante(Long id, String nome);
    Restaurante atualizarEnderecoRestaurante(Long id, Endereco endereco);
    List<Produto> verListaDeProdutosRestaurante(Long id);
    void deletarRestaurante(Long id);

}
