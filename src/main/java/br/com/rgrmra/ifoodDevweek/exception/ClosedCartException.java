package br.com.rgrmra.ifoodDevweek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ClosedCartException extends RuntimeException {
    public ClosedCartException(Long cartId) {
        super("Cart " + cartId + " is already closed!");
        System.err.println("Attempt to manage the cart " + cartId + " that is already closed!");
    }
}
