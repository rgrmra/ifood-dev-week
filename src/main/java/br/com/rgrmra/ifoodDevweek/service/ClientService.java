package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.model.Client;
import br.com.rgrmra.ifoodDevweek.model.Address;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ClientDto;

import java.util.List;

public interface ClientService {

    List<Client> listClients();
    Client addClient(ClientDto clientDto);
    Client getClientById(Long id);
    String getClientNameById(Long id);
    Address getClientAddressById(Long id);
    Client updateClient(Long id, ClientDto clientDto);
    Client updateClientName(Long id, String nome);
    Client updateClientAddress(Long id, Address address);
    void deleteClient(Long id);
}
