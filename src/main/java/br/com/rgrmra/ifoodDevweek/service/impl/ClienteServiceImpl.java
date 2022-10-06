package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.model.Cliente;
import br.com.rgrmra.ifoodDevweek.model.Endereco;
import br.com.rgrmra.ifoodDevweek.repository.ClienteRepository;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ClienteDto;
import br.com.rgrmra.ifoodDevweek.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public Cliente adicionarCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDto.getNome());
        cliente.setEndereco(clienteDto.getEndereco());
        return clienteRepository.save(cliente);
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
        clienteRepository.deleteById(id);
    }
}
