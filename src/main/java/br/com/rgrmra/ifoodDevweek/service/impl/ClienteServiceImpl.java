package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.model.Cliente;
import br.com.rgrmra.ifoodDevweek.model.Endereco;
import br.com.rgrmra.ifoodDevweek.model.Sacola;
import br.com.rgrmra.ifoodDevweek.repository.ClienteRepository;
import br.com.rgrmra.ifoodDevweek.repository.SacolaRepository;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ClienteDto;
import br.com.rgrmra.ifoodDevweek.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final SacolaRepository sacolaRepository;

    @Override
    public List<Cliente> verClientes() {
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
        listaSacolas.removeIf(sacola -> (sacola.getCliente().getId() != id));
        sacolaRepository.deleteAll(listaSacolas);
        clienteRepository.deleteById(id);
    }
}
