package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.model.Cliente;
import br.com.rgrmra.ifoodDevweek.model.Endereco;
import br.com.rgrmra.ifoodDevweek.model.Item;
import br.com.rgrmra.ifoodDevweek.model.Sacola;
import br.com.rgrmra.ifoodDevweek.repository.ClienteRepository;
import br.com.rgrmra.ifoodDevweek.repository.ItemRepository;
import br.com.rgrmra.ifoodDevweek.repository.SacolaRepository;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ClienteDto;
import br.com.rgrmra.ifoodDevweek.service.ClienteService;
import br.com.rgrmra.ifoodDevweek.service.SacolaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final SacolaRepository sacolaRepository;
    private final ItemRepository itemRepository;

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente adicionarCliente(ClienteDto clienteDto) {
        return clienteRepository.save(Cliente.builder()
                .nome(clienteDto.getNome())
                .endereco(clienteDto.getEndereco())
                .build());
    }

    @Override
    public Cliente verCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Cliente não existe!");
                }
        );
    }

    @Override
    public String verNomeCliente(Long id) {
        return verCliente(id).getNome();
    }

    @Override
    public Endereco verEnderecoCliente(Long id) {
        return verCliente(id).getEndereco();
    }

    @Override
    public Cliente atualizarCliente(Long id, ClienteDto clienteDto) {
        Cliente cliente = verCliente(id);
        cliente.setNome(clienteDto.getNome());
        cliente.setEndereco(clienteDto.getEndereco());
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualiarNomeCliente(Long id, String nome) {
        Cliente cliente = verCliente(id);
        cliente.setNome(nome);
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente atualizarEnderecoCliente(Long id, Endereco endereco) {
        Cliente cliente = verCliente(id);
        cliente.setEndereco(endereco);
        return clienteRepository.save(cliente);
    }

    @Override
    public void deletarCliente(Long id) {
        List<Sacola> listaSacolas = sacolaRepository.findAll();
        for (Sacola sacola : listaSacolas) {
            if (sacola.getCliente().getId() == id) {
                List<Item> listaItems = itemRepository.findAll();
                sacola.setItens(null);
                sacolaRepository.save(sacola);
                listaItems.removeIf(item -> !(item.getSacola().getId() == sacola.getId()));
                itemRepository.deleteAll(listaItems);
            }
        }
        listaSacolas.removeIf(sacola -> !(sacola.getCliente().getId() == id));
        sacolaRepository.deleteAll(listaSacolas);
        clienteRepository.delete(verCliente(id));
    }
}
