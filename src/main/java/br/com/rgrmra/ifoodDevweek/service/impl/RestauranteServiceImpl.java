package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.model.Endereco;
import br.com.rgrmra.ifoodDevweek.model.Produto;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.repository.ProdutoRepository;
import br.com.rgrmra.ifoodDevweek.repository.RestauranteRepository;
import br.com.rgrmra.ifoodDevweek.resorce.dto.RestauranteDto;
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
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteDto.getNome());
        restaurante.setEndereco(restauranteDto.getEndereco());
        return restauranteRepository.save(restaurante);
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
    public List<Produto> verListaDeProdutos(Long id) {
        List<Produto> listaProdutos = produtoRepository.findAll();
        listaProdutos.removeIf(produto -> !produto.getRestaurante().equals(verRestaurante(id)));
        return listaProdutos;
    }

    @Override
    public void deletarRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }
}
