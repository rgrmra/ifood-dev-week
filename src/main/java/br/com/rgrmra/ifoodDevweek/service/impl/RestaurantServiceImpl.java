package br.com.rgrmra.ifoodDevweek.service.impl;

import br.com.rgrmra.ifoodDevweek.model.Address;
import br.com.rgrmra.ifoodDevweek.model.Product;
import br.com.rgrmra.ifoodDevweek.model.Restaurant;
import br.com.rgrmra.ifoodDevweek.repository.ProdutRepository;
import br.com.rgrmra.ifoodDevweek.repository.RestaurantRepository;
import br.com.rgrmra.ifoodDevweek.resorce.dto.RestaurantDto;
import br.com.rgrmra.ifoodDevweek.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ProdutRepository productRepository;

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant addRestaurant(RestaurantDto restaurantDto) {
        return restaurantRepository.save(Restaurant.builder()
                .name(restaurantDto.getName())
                .address(restaurantDto.getAddress())
                .build());
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Restaurant doesn't exist!");
                }
        );
    }
    @Override
    public List<Restaurant> searchRestaurant(String name) {
        List<Restaurant> restaurantsList = getAllRestaurants();
        restaurantsList.removeIf(restaurant -> !restaurant.getName().toUpperCase().contains(name.toUpperCase()));
        return restaurantsList;
    }

    @Override
    public Restaurant updateRestaurant(Long id, RestaurantDto restaurantDto) {
        Restaurant restaurant = getRestaurantById(id);
        if (!restaurantDto.getName().isEmpty())
            restaurant.setName(restaurantDto.getName());

        Address address = restaurant.getAddress();
        if (!restaurantDto.getAddress().getAddress().isEmpty())
            address.setAddress(restaurantDto.getAddress().getAddress());
        if (!restaurantDto.getAddress().getSecondAddress().isEmpty())
            address.setSecondAddress(restaurantDto.getAddress().getSecondAddress());
        if (!restaurantDto.getAddress().getCity().isEmpty())
            address.setCity(restaurantDto.getAddress().getCity());
        if (!restaurantDto.getAddress().getState().isEmpty())
            address.setState(restaurantDto.getAddress().getState());
        if (!restaurantDto.getAddress().getZipCode().isEmpty())
            address.setZipCode(restaurantDto.getAddress().getZipCode());
        if (!(restaurantDto.getAddress().getNumber() == 0))
            address.setNumber(restaurantDto.getAddress().getNumber());
        if (!restaurant.getAddress().equals(address))
            restaurant.setAddress(address);

        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Product> getRestaurantProductsList(Long id) {
        return getRestaurantById(id).getProducts();
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        for (Product product : getRestaurantProductsList(restaurantId)) {
            if (product.getRestaurant().getId() == restaurantId) {
                productRepository.deleteById(product.getId());
            }
        }
        restaurantRepository.deleteById(restaurantId);
    }
}
