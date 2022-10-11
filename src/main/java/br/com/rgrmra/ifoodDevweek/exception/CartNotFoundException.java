package br.com.rgrmra.ifoodDevweek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(Long cartId) {
        super("Cart not found with id: " + cartId);
        System.err.println("Attempt to find a cart with id " + cartId +
                ", but the cart's reference id doesn't exist!");
    }
}
