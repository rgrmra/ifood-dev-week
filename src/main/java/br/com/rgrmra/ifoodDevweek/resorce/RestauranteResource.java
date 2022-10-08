package br.com.rgrmra.ifoodDevweek.resorce;

import br.com.rgrmra.ifoodDevweek.model.Endereco;
import br.com.rgrmra.ifoodDevweek.model.Produto;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.resorce.dto.RestauranteDto;
import br.com.rgrmra.ifoodDevweek.service.RestauranteService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="/ifood-dev-week/restaurantes")
@RestController
@RequestMapping("/ifood-dev-week/restaurantes")
@RequiredArgsConstructor
public class RestauranteResource {
    private final RestauranteService restauranteService;

    @GetMapping
    public List<Restaurante> listarRestaurantes() {
        return restauranteService.listarRestaurantes();
    }

    @PostMapping()
    public Restaurante adicionarRestaurante(@RequestBody RestauranteDto restauranteDto) {
        return restauranteService.adicionarRestaurante(restauranteDto);
    }

    @GetMapping("/{id}")
    public Restaurante verRestaurante(@PathVariable("id") Long id) {
        return restauranteService.verRestaurante(id);
    }

    @GetMapping("/{id}/nome")
    public String verNomeRestaurante(@PathVariable("id") Long id) {
        return restauranteService.verNomeRestaurante(id);
    }

    @GetMapping("/{id}/endereco")
    public Endereco verEnderecoRestaurante(@PathVariable("id") Long id) {
        return restauranteService.verEnderecoRestaurante(id);
    }

    @GetMapping("/pesquisar/{nome}")
    public List<Restaurante> pesquisarRestaurante(@PathVariable("nome") String nome) {
        return restauranteService.pesquisarRestaurante(nome);
    }

    @GetMapping("/{id}/produtos")
    public List<Produto> verListaDeProdutosRestaurante(@PathVariable("id") Long id) {
        return restauranteService.verListaDeProdutosRestaurante(id);
    }

    @PutMapping("/{id}/atualizar")
    public Restaurante atualizarRestaurante(@PathVariable("id") Long id, @RequestBody RestauranteDto restauranteDto) {
        return restauranteService.atualizarRestaurante(id, restauranteDto);
    }

    @PatchMapping("/{id}/atualizar-nome")
    public Restaurante atualizarNomeRestaurante(@PathVariable("id") Long id, @RequestBody String nome) {
        return restauranteService.atualizarNomeRestaurante(id, nome);
    }

    @PatchMapping("/{id}/atualizar-endereco")
    public Restaurante atualizarEnderecoRestaurante(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
        return restauranteService.atualizarEnderecoRestaurante(id, endereco);
    }

    @DeleteMapping("/{id}/deletar")
    public void deletarRestaurante(@PathVariable("id") Long id) {
        restauranteService.deletarRestaurante(id);
    }

}
