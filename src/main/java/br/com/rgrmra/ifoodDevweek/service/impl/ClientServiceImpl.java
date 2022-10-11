package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.model.Client;
import br.com.rgrmra.ifoodDevweek.model.Address;
import br.com.rgrmra.ifoodDevweek.model.Cart;
import br.com.rgrmra.ifoodDevweek.repository.CartRepository;
import br.com.rgrmra.ifoodDevweek.repository.ClientRepository;
import br.com.rgrmra.ifoodDevweek.resorce.dto.ClientDto;
import br.com.rgrmra.ifoodDevweek.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CartRepository cartRepository;

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client addClient(ClientDto clientDto) {
        return clientRepository.save(Client.builder()
                .name(clientDto.getName())
                .address(clientDto.getAddress())
                .build());
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Client doesn't exist!");
                }
        );
    }

    @Override
    public Client updateClient(Long id, ClientDto clientDto) {
        Client client = getClientById(id);
        if (!clientDto.getName().isEmpty())
            client.setName(clientDto.getName());

        Address address = client.getAddress();
        if (!clientDto.getAddress().getAddress().isEmpty())
            address.setAddress(clientDto.getAddress().getAddress());
        if (!clientDto.getAddress().getSecondAddress().isEmpty())
            address.setSecondAddress(clientDto.getAddress().getSecondAddress());
        if (!clientDto.getAddress().getCity().isEmpty())
            address.setCity(clientDto.getAddress().getCity());
        if (!clientDto.getAddress().getState().isEmpty())
            address.setState(clientDto.getAddress().getState());
        if (!clientDto.getAddress().getZipCode().isEmpty())
            address.setZipCode(clientDto.getAddress().getZipCode());
        if (!(clientDto.getAddress().getNumber() == 0))
            address.setNumber(clientDto.getAddress().getNumber());
        if (!client.getAddress().equals(address))
            client.setAddress(address);

        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long clientId) {
        for (Cart cart : cartRepository.findAll()) {
            if (cart.getClient().getId() == clientId) {
                cartRepository.deleteById(cart.getId());
            }
        }
        clientRepository.delete(getClientById(clientId));
    }
}
