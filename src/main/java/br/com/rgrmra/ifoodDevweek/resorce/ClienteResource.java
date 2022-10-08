package br.com.rgrmra.ifoodDevweek.resorce;

import br.com.rgrmra.ifoodDevweek.model.Cliente;
import br.com.rgrmra.ifoodDevweek.model.Endereco;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ClienteDto;
import br.com.rgrmra.ifoodDevweek.service.ClienteService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="/ifood-dev-week/clientes")
@RestController
@RequestMapping("/ifood-dev-week/clientes")
@RequiredArgsConstructor
public class ClienteResource {

    private final ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @PostMapping()
    public Cliente adicionarCliente(@RequestBody ClienteDto clienteDto) {
        return clienteService.adicionarCliente(clienteDto);
    }

    @GetMapping("/{id}")
    public Cliente verCliente(@PathVariable("id") Long id) {
        return clienteService.verCliente(id);
    }

    @GetMapping("/{id}/nome")
    public String verNomeCliente(@PathVariable("id") Long id) {
        return clienteService.verNomeCliente(id);
    }

    @GetMapping("/{id}/endereco")
    public Endereco verEnderecoCliente(@PathVariable("id") Long id) {
        return clienteService.verEnderecoCliente(id);
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable("id") Long id, @RequestBody ClienteDto clienteDto) {
        return clienteService.atualizarCliente(id, clienteDto);
    }

    @PatchMapping("/{id}/nome")
    public Cliente atualizarNomeCliente(@PathVariable("id") Long id, @RequestBody String nome) {
        return clienteService.atualiarNomeCliente(id, nome);
    }

    @PatchMapping("/{id}/endereco")
    public Cliente atualizarEnderecoCliente(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
        return clienteService.atualizarEnderecoCliente(id, endereco);
    }

    @DeleteMapping("/{id}")
    public void deletarCliente(@PathVariable("id") Long id) {
        clienteService.deletarCliente(id);
    }

}
