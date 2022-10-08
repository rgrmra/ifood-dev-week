package br.com.rgrmra.ifoodDevweek.resorce;

import br.com.rgrmra.ifoodDevweek.model.Client;
import br.com.rgrmra.ifoodDevweek.model.Address;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ClientDto;
import br.com.rgrmra.ifoodDevweek.service.ClientService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="/ifood-dev-week/clients")
@RestController
@RequestMapping("/ifood-dev-week/clients")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService clientService;

    @GetMapping
    public List<Client> listClients() {
        return clientService.listClients();
    }

    @PostMapping()
    public Client addClients(@RequestBody ClientDto clientDto) {
        return clientService.addClient(clientDto);
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable("id") Long id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/{id}/name")
    public String getClientNameById(@PathVariable("id") Long id) {
        return clientService.getClientNameById(id);
    }

    @GetMapping("/{id}/address")
    public Address getClientAddressById(@PathVariable("id") Long id) {
        return clientService.getClientAddressById(id);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable("id") Long id, @RequestBody ClientDto clientDto) {
        return clientService.updateClient(id, clientDto);
    }

    @PatchMapping("/{id}/name")
    public Client updateClientName(@PathVariable("id") Long id, @RequestParam String name) {
        return clientService.updateClientName(id, name);
    }

    @PatchMapping("/{id}/address")
    public Client updateClientAddress(@PathVariable("id") Long id, @RequestBody Address address) {
        return clientService.updateClientAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
    }

}
