package br.com.rgrmra.ifoodDevweek.resorce;

import br.com.rgrmra.ifoodDevweek.model.Produto;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ProdutoDto;
import br.com.rgrmra.ifoodDevweek.service.ProdutoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="/ifood-dev-week/produtos")
@RestController
@RequestMapping("/ifood-dev-week/produtos")
@RequiredArgsConstructor
public class ProdutoResource {
    private final ProdutoService produtoService;

    @GetMapping
    public List<Produto> verProdutos() {
        return produtoService.verProdutos();
    }

    @PostMapping()
    public Produto adicionarProduto(@RequestBody ProdutoDto produtoDto) {
        return produtoService.adicionarProduto(produtoDto);
    }

    @GetMapping("/{id}")
    public Produto verProduto(@PathVariable("id") Long id) {
        return produtoService.verProduto(id);
    }

    @GetMapping("/{id}/nome")
    public String verNomeProduto(@PathVariable("id") Long id) {
        return produtoService.verNomeProduto(id);
    }

    @GetMapping("/{id}/valor")
    public double verValorUnitarioProduto(@PathVariable("id") Long id) {
        return produtoService.verValorUnitarioProduto(id);
    }

    @GetMapping("/{id}/disponibilidade")
    public boolean verDisponibilidadeProduto(@PathVariable("id") Long id) {
        return produtoService.verDisponibilidadeProduto(id);
    }

    @GetMapping("/{id}/restaurante")
    public Restaurante verRestauranteProduto(@PathVariable("id") Long id) {
        return produtoService.verRestauranteProduto(id);
    }

    @GetMapping("/pesquisar/{nome}")
    public List<Produto> pesquisarRestaurante(@PathVariable("nome") String nome) {
        return produtoService.pesquisarProduto(nome);
    }

    @PutMapping("/{id}/atualizar")
    public Produto atualizarProduto(@PathVariable("id") Long id, @RequestBody ProdutoDto produtoDto) {
        return produtoService.atualizarProduto(id, produtoDto);
    }

    @PatchMapping("/{id}/atualizar-nome")
    public Produto atualizarNomeProduto(@PathVariable("id") Long id, @RequestBody String nome) {
        return produtoService.atualizarNomeProduto(id, nome);
    }

    @PatchMapping("/{id}/atualizar-valor")
    public Produto atualizarValorUnitarioProduto(@PathVariable("id") Long id, double valorUnitario) {
        return produtoService.atualizarValorUnitarioProduto(id, valorUnitario);
    }

    @PatchMapping("/{id}/atualizar-disponibilidade")
    public Produto atualizarDisponibilidadeProduto(@PathVariable("id") Long id, boolean disponivel) {
        return produtoService.atualizarDisponibilidadeProduto(id, disponivel);
    }

    @PatchMapping("/{id}/atualizar-restaurante")
    public Produto atualizarRestauranteProduto(@PathVariable("id") Long id, Long restauranteId) {
        return produtoService.atualizarResturantePorduto(id, restauranteId);
    }

    @DeleteMapping("/{id}/deletar")
    public void deletarProduto(@PathVariable("id") Long id) {
        produtoService.deletarProduto(id);
    }
}
