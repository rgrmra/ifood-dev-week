package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.model.Cliente;
import br.com.rgrmra.ifoodDevweek.model.Endereco;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ClienteDto;

import java.util.List;

public interface ClienteService {

    List<Cliente> verClientes();
    Cliente adicionarCliente(ClienteDto clienteDto);
    Cliente verCliente(Long id);
    String verNomeCliente(Long id);
    Endereco verEnderecoCliente(Long id);
    Cliente atualizarCliente(Long id, ClienteDto clienteDto);
    Cliente atualiarNomeCliente(Long id, String nome);
    Cliente atualizarEnderecoCliente(Long id, Endereco endereco);
    void deletarCliente(Long id);
}
