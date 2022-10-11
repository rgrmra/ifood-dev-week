package br.com.rgrmra.ifoodDevweek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(Long restaurantId) {
        super("Restaurant not found with id: " + restaurantId);
        System.err.println("Attempt to find a restaurant with id " + restaurantId +
                ", but the restaurant's reference id doesn't exist!");
    }
}
