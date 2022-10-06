package br.com.rgrmra.ifoodDevweek.resorce;

import br.com.rgrmra.ifoodDevweek.model.Produto;
import br.com.rgrmra.ifoodDevweek.model.Restaurante;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ProdutoDto;
import br.com.rgrmra.ifoodDevweek.service.ProdutoService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value="/ifood-dev-week/produtos")
@RestController
@RequestMapping("/ifood-dev-week/produtos")
@RequiredArgsConstructor
public class ProdutoResource {
    private final ProdutoService produtoService;

    @PostMapping()
    public Produto adicionarProduto(@RequestBody ProdutoDto produtoDto) {
        return produtoService.adicionarProduto(produtoDto);
    }

    @GetMapping("/{id}")
    public Produto verProduto(@PathVariable("id") Long id) {
        return produtoService.verProduto(id);
    }

    @GetMapping("/{id}/restaurante")
    public Restaurante verRestauranteProduto(@PathVariable("id") Long id) {
        return produtoService.verRestauranteProduto(id);
    }

    @PutMapping("/{id}/atualizar")
    public Produto atualizarProduto(@PathVariable("id") Long id, @RequestBody ProdutoDto produtoDto) {
        return produtoService.atualizarProduto(id, produtoDto);
    }

    @DeleteMapping("/{id}/deletar")
    public void deletarProduto(@PathVariable("id") Long id) {
        produtoService.deletarProduto(id);
    }
}
