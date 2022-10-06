package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.model.Produto;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ProdutoDto;

public interface ProdutoService {

    Produto adicionarProduto(ProdutoDto produtoDto);
    Produto verProduto(Long id);
    String verNomeProduto(Long id);
    double verValorUnitarioProduto(Long id);
    boolean verDisponibilidadeProduto(Long id);
    Restaurante verRestauranteProduto(Long id);
    Produto atualizarProduto(Long id, ProdutoDto produtoDto);
    Produto atualizarNomeProduto(Long id, String nome);
    Produto atualizarValorUnitarioProduto(Long id, double valorUnitario);
    Produto atualizarDisponibilidadeProduto(Long id, boolean disponivel);
    Produto atualizarResturantePorduto(Long id, Long restauranteId);
    void deletarProduto(Long id);
}
