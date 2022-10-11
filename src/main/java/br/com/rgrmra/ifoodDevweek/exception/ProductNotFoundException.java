package br.com.rgrmra.ifoodDevweek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(long productId) {
        super("Product not found with id: " + productId);
        System.err.println("Attempt to find a product by id " + productId +
                ", but the product's reference id doesn't exist!");
    }
}
