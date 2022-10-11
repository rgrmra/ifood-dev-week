package br.com.rgrmra.ifoodDevweek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NullPriceException extends RuntimeException {
    public NullPriceException() {
        super("Null Price Exception!");
        System.err.println("Attempt to create a product without price!");
    }
}
