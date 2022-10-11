package br.com.rgrmra.ifoodDevweek.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(Long clientId) {
        super("Client not found with id: " + clientId);
        System.err.println("Attempt to find a client with id " + clientId +
                ", but the client's reference id doesn't exist!");
    }
}
