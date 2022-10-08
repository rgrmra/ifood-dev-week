package br.com.rgrmra.ifoodDevweek.resorce;

import br.com.rgrmra.ifoodDevweek.model.Address;
import br.com.rgrmra.ifoodDevweek.model.Product;
import br.com.rgrmra.ifoodDevweek.model.Restaurant;
import br.com.rgrmra.ifoodDevweek.resorce.dto.RestaurantDto;
import br.com.rgrmra.ifoodDevweek.service.RestaurantService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="/ifood-dev-week/restaurants")
@RestController
@RequestMapping("/ifood-dev-week/restaurants")
@RequiredArgsConstructor
public class RestaurantResource {
    private final RestaurantService restaurantService;

    @GetMapping
    public List<Restaurant> listRestaurants() {
        return restaurantService.listRestaurants();
    }

    @PostMapping()
    public Restaurant addRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return restaurantService.addRestaurant(restaurantDto);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable("id") Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @GetMapping("/{id}/name")
    public String getRestaurantNameById(@PathVariable("id") Long id) {
        return restaurantService.getRestaurantNameById(id);
    }

    @GetMapping("/{id}/address")
    public Address getRestaurantAddressById(@PathVariable("id") Long id) {
        return restaurantService.getRestaurantAddressById(id);
    }

    @GetMapping("/search/{name}")
    public List<Restaurant> searchRestaurants(@PathVariable("name") String name) {
        return restaurantService.searchRestaurant(name);
    }

    @GetMapping("/{id}/products")
    public List<Product> getRestaurantProductsList(@PathVariable("id") Long id) {
        return restaurantService.getRestaurantProductsList(id);
    }

    @PutMapping("/{id}")
    public Restaurant updateRestaurant(@PathVariable("id") Long id, @RequestBody RestaurantDto restaurantDto) {
        return restaurantService.updateRestaurant(id, restaurantDto);
    }

    @PatchMapping("/{id}/name")
    public Restaurant updateRestaurantName(@PathVariable("id") Long id, @RequestBody String name) {
        return restaurantService.updateRestaurantName(id, name);
    }

    @PatchMapping("/{id}/address")
    public Restaurant updateRestaurantAddress(@PathVariable("id") Long id, @RequestBody Address address) {
        return restaurantService.updateRestaurantAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable("id") Long id) {
        restaurantService.deleteRestaurant(id);
    }

}
