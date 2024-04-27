package edu.tienda.core.controllers;

import edu.tienda.core.domain.Client;
import edu.tienda.core.exceptions.BadRequestException;
import edu.tienda.core.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/clients")
public class ClientRestController {

    private static final String CLIENT_NOT_FOUND = "Client %s not found";
    private static final String CLIENT_ALREADY_EXISTS = "Client %s already exists";

    private List<Client> clients = new ArrayList<>(Arrays.asList(
            new Client("john", "19578aks", "john@mail.com"),
            new Client("cena", "356wdlq9", "mary@mail.com"),
            new Client("robert", "32q5asdq5", "peter@mail.com")
    ));

    @GetMapping
    public ResponseEntity<?> getClients() {
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getClient(@PathVariable String username) {
        return clients.stream()
                .filter(client -> client.getUsername().equalsIgnoreCase(username))
                .findFirst().map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CLIENT_NOT_FOUND, username)));
    }

    @PutMapping
    ResponseEntity<?> updateClient(@RequestBody Client client) {
        if (client.getPassword().length() < 8 ) {
            throw new BadRequestException("Password must have at least 8 characters");
        }
        if (!isValidEmail(client.getEmail())) {
            throw new BadRequestException("Invalid email");
        }
        Client foundedClient = clients.stream()
                .filter(cli -> cli.getUsername().equalsIgnoreCase(client.getUsername()))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException(String.format(CLIENT_NOT_FOUND, client)));
        foundedClient.setPassword(client.getPassword());
        foundedClient.setEmail(client.getEmail());

        return ResponseEntity.ok(foundedClient);
    }

    @PostMapping
    public ResponseEntity<?> addClient(@RequestBody Client client) {
        if (clients.stream().anyMatch(cli -> cli.getUsername().equalsIgnoreCase(client.getUsername()))) {
            throw new BadRequestException(String.format(CLIENT_ALREADY_EXISTS, client.getUsername()));
        }
        clients.add(client);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(client.getUsername())
                .toUri();

        return ResponseEntity.created(location).body(client);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity deleteClient(@PathVariable String username) {
        Client foundedClient = clients.stream()
                .filter(client -> client.getUsername().equalsIgnoreCase(username))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException(String.format(CLIENT_NOT_FOUND, username)));
        System.out.println("Client removed: " + foundedClient.getUsername());
        clients.removeIf(client -> client.getUsername().equalsIgnoreCase(username));

        return ResponseEntity.noContent().build();
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}
