package br.com.rgrmra.ifoodDevweek.service;

import br.com.rgrmra.ifoodDevweek.model.Address;
import br.com.rgrmra.ifoodDevweek.model.Product;
import br.com.rgrmra.ifoodDevweek.model.Restaurant;
import br.com.rgrmra.ifoodDevweek.resorce.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {

    List<Restaurant> listRestaurants();
    Restaurant addRestaurant(RestaurantDto restaurantDto);
    Restaurant getRestaurantById(Long id);
    String getRestaurantNameById(Long id);
    Address getRestaurantAddressById(Long id);
    List<Restaurant> searchRestaurant(String nome);
    Restaurant updateRestaurant(Long id, RestaurantDto restaurantDto);
    Restaurant updateRestaurantName(Long id, String nome);
    Restaurant updateRestaurantAddress(Long id, Address address);
    List<Product> getRestaurantProductsList(Long id);
    void deleteRestaurant(Long id);

}
