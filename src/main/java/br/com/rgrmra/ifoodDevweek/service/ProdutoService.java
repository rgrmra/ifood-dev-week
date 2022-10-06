package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.model.Produto;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ProdutoDto;

public interface ProdutoService {

    Produto adicionarProduto(ProdutoDto produtoDto);
    Produto verProduto(Long id);
    Restaurante verRestauranteProduto(Long id);
    Produto atualizarProduto(Long id, ProdutoDto produtoDto);
    void deletarProduto(Long id);
}
