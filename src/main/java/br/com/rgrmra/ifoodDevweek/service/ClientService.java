package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.model.Client;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ClientDto;

import java.util.List;

public interface ClientService {

    List<Client> getAllClients();
    Client addClient(ClientDto clientDto);
    Client getClientById(Long id);
    Client updateClient(Long id, ClientDto clientDto);
    void deleteClient(Long id);
}
