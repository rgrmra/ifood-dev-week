package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.model.Produto;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.repository.ProdutoRepository;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ProdutoDto;
import br.com.rgrmra.ifoodDevweek.service.ProdutoService;
import br.com.rgrmra.ifoodDevweek.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final RestauranteService restauranteService;

    @Override
    public Produto adicionarProduto(ProdutoDto produtoDto) {
        Produto produto = new Produto();
        produto.setNome(produtoDto.getNome());
        produto.setValorUnitario(produtoDto.getValorUnitario());
        produto.setDisponivel(produtoDto.isDisponivel());
        produto.setRestaurante(restauranteService.verRestaurante(produtoDto.getRestauranteId()));
        return produtoRepository.save(produto);
    }

    @Override
    public Produto verProduto(Long id) {
        return produtoRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Produto não existe!");
                }
        );
    }
    @Override
    public Restaurante verRestauranteProduto(Long id) {
        return verProduto(id).getRestaurante();
    }

    @Override
    public Produto atualizarProduto(Long id, ProdutoDto produtoDto) {
        Produto produto = verProduto(id);
        produto.setNome(produtoDto.getNome());
        produto.setValorUnitario(produtoDto.getValorUnitario());
        produto.setDisponivel(produtoDto.isDisponivel());
        produto.setRestaurante(restauranteService.verRestaurante(produtoDto.getRestauranteId()));
        return produtoRepository.save(produto);
    }

    @Override
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
