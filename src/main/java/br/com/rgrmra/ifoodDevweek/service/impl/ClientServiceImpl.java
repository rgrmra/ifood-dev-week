package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.model.Client;
import br.com.rgrmra.ifoodDevweek.model.Address;
import br.com.rgrmra.ifoodDevweek.model.Item;
import br.com.rgrmra.ifoodDevweek.model.Cart;
import br.com.rgrmra.ifoodDevweek.repository.ClientRepository;
import br.com.rgrmra.ifoodDevweek.repository.ItemRepository;
import br.com.rgrmra.ifoodDevweek.repository.CartRepository;
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
    private final ItemRepository itemRepository;

    @Override
    public List<Client> listClients() {
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
    public String getClientNameById(Long id) {
        return getClientById(id).getName();
    }

    @Override
    public Address getClientAddressById(Long id) {
        return getClientById(id).getAddress();
    }

    @Override
    public Client updateClient(Long id, ClientDto clientDto) {
        Client client = getClientById(id);
        client.setName(clientDto.getName());
        client.setAddress(clientDto.getAddress());
        return clientRepository.save(client);
    }

    @Override
    public Client updateClientName(Long id, String name) {
        Client client = getClientById(id);
        client.setName(name);
        return clientRepository.save(client);
    }

    @Override
    public Client updateClientAddress(Long id, Address address) {
        Client client = getClientById(id);
        client.setAddress(address);
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        List<Cart> cartsList = cartRepository.findAll();
        for (Cart cart : cartsList) {
            if (cart.getClient().getId() == id) {
                List<Item> itemsList = itemRepository.findAll();
                cart.setItens(null);
                cartRepository.save(cart);
                itemsList.removeIf(item -> !(item.getCart().getId() == cart.getId()));
                itemRepository.deleteAll(itemsList);
            }
        }
        cartsList.removeIf(cart -> !(cart.getClient().getId() == id));
        cartRepository.deleteAll(cartsList);
        clientRepository.delete(getClientById(id));
    }
}
