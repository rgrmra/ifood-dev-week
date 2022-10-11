package br.com.rgrmra.ifoodDevweek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DifferentRestaurantException extends RuntimeException{
    public DifferentRestaurantException(Long cartId, Long cartRestaurantId, Long itemRestaurantId) {
        super("Attempt to add an item from a different restaurant in the cart " + cartId);
        System.err.println("Attempt to add an item from the restaurant id " + itemRestaurantId +
                " in the cart " + cartId + " with item(s) from the restaurant id " + cartRestaurantId);
    }
}
