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
    public String verNomeProduto(Long id) {
        return verProduto(id).getNome();
    }

    @Override
    public double verValorUnitarioProduto(Long id) {
        return verProduto(id).getValorUnitario();
    }

    @Override
    public boolean verDisponibilidadeProduto(Long id) {
        return verProduto(id).isDisponivel();
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
    public Produto atualizarNomeProduto(Long id, String nome) {
        Produto produto = verProduto(id);
        produto.setNome(nome);
        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizarValorUnitarioProduto(Long id, double valorUnitario) {
        Produto produto = verProduto(id);
        produto.setValorUnitario(valorUnitario);
        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizarDisponibilidadeProduto(Long id, boolean disponivel) {
        Produto produto = verProduto(id);
        produto.setDisponivel(disponivel);
        return produtoRepository.save(produto);
    }

    @Override
    public Produto atualizarResturantePorduto(Long id, Long restauranteId) {
        Produto produto = verProduto(id);
        produto.setRestaurante(restauranteService.verRestaurante(id));
        return produtoRepository.save(produto);
    }

    @Override
    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
