package br.com.rgrmra.ifoodDevweek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductNotAvailableException extends RuntimeException {
    public ProductNotAvailableException(Long cartId, Long itemId) {
        super("Item id " + itemId + "not available!");
        System.err.println("Attempt to add a item " + itemId + " that is not available in cart " + cartId);
    }
}
