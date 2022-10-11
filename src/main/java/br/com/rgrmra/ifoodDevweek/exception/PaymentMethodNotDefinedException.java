package br.com.rgrmra.ifoodDevweek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PaymentMethodNotDefinedException extends RuntimeException {
    public PaymentMethodNotDefinedException(Long cartId) {
        super("Payment method not define in cart id: " + cartId);
        System.err.println("Attempt to close the cart without define a payment method in the cart " + cartId);
    }
}
