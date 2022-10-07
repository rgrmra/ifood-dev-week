package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.model.Produto;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.repository.ProdutoRepository;
import br.com.rgrmra.ifoodDevweek.repository.RestauranteRepository;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ProdutoDto;
import br.com.rgrmra.ifoodDevweek.service.ProdutoService;
import br.com.rgrmra.ifoodDevweek.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;

    @Override
    public Produto adicionarProduto(ProdutoDto produtoDto) {
        Produto produto = new Produto();
        produto.setNome(produtoDto.getNome());
        produto.setValorUnitario(produtoDto.getValorUnitario());
        produto.setDisponivel(produtoDto.isDisponivel());
        produto.setRestaurante(restauranteRepository.findById(produtoDto.getRestauranteId()).orElseThrow(
                () -> {
                    throw new RuntimeException("Restaurante não existe!");
                }
        ));

        return atualizarListaRestaurante(produtoRepository.save(produto));
    }

    private Produto atualizarListaRestaurante(Produto produtoNovo) {
        Restaurante restaurante = restauranteRepository.findById(produtoNovo.getRestaurante().getId()).orElseThrow(
                () -> {
                    throw new RuntimeException("Restaurante não existe");
                }
        );
        List<Produto> listaProdutos = produtoRepository.findAll();
        listaProdutos.removeIf(produto -> !produto.getRestaurante().equals(restaurante));
        restaurante.setProdutos(listaProdutos);
        restauranteRepository.save(restaurante);
        return produtoNovo;
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
    public List<Produto> pesquisarProduto(String nome) {
        List<Produto> listaProdutos = produtoRepository.findAll();
        listaProdutos.removeIf(produto -> !produto.getNome().contains(nome));
        return listaProdutos;
    }

    @Override
    public Produto atualizarProduto(Long id, ProdutoDto produtoDto) {
        Produto produto = verProduto(id);
        produto.setNome(produtoDto.getNome());
        produto.setValorUnitario(produtoDto.getValorUnitario());
        produto.setDisponivel(produtoDto.isDisponivel());
        produto.setRestaurante(restauranteRepository.findById(produtoDto.getRestauranteId()).orElseThrow(
                () -> {
                    throw new RuntimeException("Restaurante não existe!");
                }
        ));

        return atualizarListaRestaurante(produtoRepository.save(produto));
    }

    @Override
    public Produto atualizarNomeProduto(Long id, String nome) {
        Produto produto = verProduto(id);
        produto.setNome(nome);

        return atualizarListaRestaurante(produtoRepository.save(produto));
    }

    @Override
    public Produto atualizarValorUnitarioProduto(Long id, double valorUnitario) {
        Produto produto = verProduto(id);
        produto.setValorUnitario(valorUnitario);
        return atualizarListaRestaurante(produtoRepository.save(produto));
    }

    @Override
    public Produto atualizarDisponibilidadeProduto(Long id, boolean disponivel) {
        Produto produto = verProduto(id);
        produto.setDisponivel(disponivel);
        return atualizarListaRestaurante(produtoRepository.save(produto));
    }

    @Override
    public Produto atualizarResturantePorduto(Long id, Long restauranteId) {
        Produto produto = verProduto(id);
        produto.setRestaurante(restauranteRepository.findById(restauranteId).orElseThrow(
                () -> {
                    throw new RuntimeException("Restaurante não existe!");
                }
        ));
        return atualizarListaRestaurante(produtoRepository.save(produto));
    }

    @Override
    public void deletarProduto(Long id) {
        Produto produto = verProduto(id);
        Restaurante restaurante = restauranteRepository.findById(produto.getRestaurante().getId()).orElseThrow();
        List<Produto> listaProdutos = restaurante.getProdutos();
        listaProdutos.remove(produto);
        restaurante.setProdutos(listaProdutos);
        restauranteRepository.save(restaurante);
        produtoRepository.deleteById(id);
    }
}
