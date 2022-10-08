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
    private final ProdutRepository produtRepository;

    @Override
    public List<Restaurant> listRestaurants() {
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
    public String getRestaurantNameById(Long id) {
        return getRestaurantById(id).getName();
    }

    @Override
    public Address getRestaurantAddressById(Long id) {
        return getRestaurantById(id).getAddress();
    }

    @Override
    public List<Restaurant> searchRestaurant(String name) {
        List<Restaurant> restaurantsList = restaurantRepository.findAll();
        restaurantsList.removeIf(restaurant -> !restaurant.getName().toUpperCase().contains(name.toUpperCase()));
        return restaurantsList;
    }

    @Override
    public Restaurant updateRestaurant(Long id, RestaurantDto restaurantDto) {
        Restaurant restaurant = getRestaurantById(id);
        restaurant.setName(restaurantDto.getName());
        restaurant.setAddress(restaurantDto.getAddress());
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurantName(Long id, String name) {
        Restaurant restaurant = getRestaurantById(id);
        restaurant.setName(name);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurantAddress(Long id, Address address) {
        Restaurant restaurant = getRestaurantById(id);
        restaurant.setAddress(address);
        return restaurantRepository.save(restaurant);
    }

    @Override
    public List<Product> getRestaurantProductsList(Long id) {
        return getRestaurantById(id).getProducts();
    }

    @Override
    public void deleteRestaurant(Long id) {
        List<Product> productsList = getRestaurantProductsList(id);
        productsList.removeIf(product -> !(product.getRestaurant().getId() == id));
        produtRepository.deleteAll(productsList);
        restaurantRepository.deleteById(id);
    }
}
