package br.com.rgrmra.ifoodDevweek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmptyCartException extends RuntimeException {
    public EmptyCartException(Long cartId) {
        super("Checkout in the empty cart " + cartId);
        System.err.println("Attempt to close the cart " + cartId + " that is empty!");
    }
}
