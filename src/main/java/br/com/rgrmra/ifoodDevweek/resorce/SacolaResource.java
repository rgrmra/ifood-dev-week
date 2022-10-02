package br.com.rgrmra.ifoodDevweek.resorce;

import br.com.rgrmra.ifoodDevweek.model.Item;
import br.com.rgrmra.ifoodDevweek.model.Sacola;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ItemDto;
import br.com.rgrmra.ifoodDevweek.service.SacolaService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(value="/ifood-dev-week/sacolas")
@RestController
@RequestMapping("/ifood-dev-week/sacolas")
@RequiredArgsConstructor
public class SacolaResource {

    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirIntemNaSacola(@RequestBody ItemDto itemDto) {
        return sacolaService.incluirItemNaSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id) {
        return sacolaService.verSacola(id);
    }

    @PatchMapping("/fechar-sacola/{sacolaId}")
    public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId, @RequestParam("formaPagamento") int formaDePagamento) {
        return sacolaService.fecharSacola(sacolaId, formaDePagamento);

    }
}
