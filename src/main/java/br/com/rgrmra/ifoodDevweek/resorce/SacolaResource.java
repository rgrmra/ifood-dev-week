package br.com.rgrmra.ifoodDevweek.resorce;

import br.com.rgrmra.ifoodDevweek.enumeration.FormaPagamento;
import br.com.rgrmra.ifoodDevweek.model.Item;
import br.com.rgrmra.ifoodDevweek.model.Sacola;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ItemDto;
import br.com.rgrmra.ifoodDevweek.service.SacolaService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="/ifood-dev-week/sacolas")
@RestController
@RequestMapping("/ifood-dev-week/sacolas")
@RequiredArgsConstructor
public class SacolaResource {

    private final SacolaService sacolaService;

    @GetMapping
    public List<Sacola> listarSacolas() {
        return sacolaService.listarSacolas();
    }

    @PostMapping
    public Sacola adicionarSacola(@RequestParam Long clienteId) {
        return sacolaService.adicionarSacola(clienteId);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id) {
        return sacolaService.verSacola(id);
    }

    @GetMapping("/cliente/{clienteId}")
    public List<Sacola> verSacolasPeloCliente(@PathVariable("clienteId") Long clienteId) {
        return sacolaService.verSacolaPeloCliente(clienteId);
    }

    @PatchMapping("{id}/incluir-item")
    public Item incluirIntemNaSacola(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
        return sacolaService.incluirItemNaSacola(id, itemDto);
    }

    @DeleteMapping("/{id}/deletar-item/{itemId}")
    public Sacola removerItemNaSacola(@PathVariable("id") Long sacolaId, @PathVariable("itemId") Long itemId) {
        return sacolaService.removerItemNaSacola(sacolaId, itemId);
    }

    @PatchMapping("/{id}/forma-pagamento")
    public Sacola formaPagamentoSacola(@PathVariable("id") Long id, @RequestParam("formaPagamento") FormaPagamento formaPagamento) {
        return sacolaService.formaPagamentoSacola(id, formaPagamento);
    }

    @PatchMapping("/{id}/fechar-sacola")
    public Sacola fecharSacola(@PathVariable("id") Long id) {
        return sacolaService.fecharSacola(id);
    }

    @DeleteMapping("/{id}")
    public void deletarSacola(@PathVariable("id") Long id) {
        sacolaService.deletarSacola(id);
    }
}
