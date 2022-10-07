package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.model.Endereco;
import br.com.rgrmra.ifoodDevweek.model.Produto;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.repository.ProdutoRepository;
import br.com.rgrmra.ifoodDevweek.repository.RestauranteRepository;
import br.com.rgrmra.ifoodDevweek.resorce.dto.RestauranteDto;
import br.com.rgrmra.ifoodDevweek.service.ProdutoService;
import br.com.rgrmra.ifoodDevweek.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public Restaurante adicionarRestaurante(RestauranteDto restauranteDto) {
        return restauranteRepository.save(Restaurante.builder()
                .nome(restauranteDto.getNome())
                .endereco(restauranteDto.getEndereco())
                .build());
    }

    @Override
    public Restaurante verRestaurante(Long id) {
        return restauranteRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("O restaurante não existe!");
                }
        );
    }

    @Override
    public String verNomeRestaurante(Long id) {
        return verRestaurante(id).getNome();
    }

    @Override
    public Endereco verEnderecoRestaurante(Long id) {
        return verRestaurante(id).getEndereco();
    }

    @Override
    public List<Restaurante> pesquisarRestaurante(String nome) {
        List<Restaurante> listaRestaurante = restauranteRepository.findAll();
        listaRestaurante.removeIf(restaurante -> !restaurante.getNome().contains(nome));
        return listaRestaurante;
    }

    @Override
    public Restaurante atualizarRestaurante(Long id, RestauranteDto restauranteDto) {
        Restaurante restaurante = verRestaurante(id);
        restaurante.setNome(restauranteDto.getNome());
        restaurante.setEndereco(restauranteDto.getEndereco());
        return restauranteRepository.save(restaurante);
    }

    @Override
    public Restaurante atualizarNomeRestaurante(Long id, String nome) {
        Restaurante restaurante = verRestaurante(id);
        restaurante.setNome(nome);
        return restauranteRepository.save(restaurante);
    }

    @Override
    public Restaurante atualizarEnderecoRestaurante(Long id, Endereco endereco) {
        Restaurante restaurante = verRestaurante(id);
        restaurante.setEndereco(endereco);
        return restauranteRepository.save(restaurante);
    }

    @Override
    public List<Produto> verListaDeProdutosRestaurante(Long id) {
        return verRestaurante(id).getProdutos();
    }

    @Override
    public void deletarRestaurante(Long id) {
        restauranteRepository.deleteById(id);
        produtoRepository.findAll().removeIf(produto -> (produto.getId() == id));
    }
}
