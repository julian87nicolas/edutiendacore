package edu.tienda.core.controllers;

import edu.tienda.core.domain.Client;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientRestController {
    private List<Client> clients = new ArrayList<>(Arrays.asList(
            new Client("john", "123", "john@mail.com"),
            new Client("mary", "456", "mary@mail.com"),
            new Client("peter", "789", "peter@mail.com")
    ));

    @GetMapping
    public ResponseEntity<?> getClients() {
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getClient(@PathVariable String username) {
        return ResponseEntity.ok(clients.stream()
                .filter(client -> client.getUsername().equalsIgnoreCase(username))
                .findFirst().orElseThrow());
    }

    @PutMapping
    ResponseEntity<?> updateClient(@RequestBody Client client) {
        Client foundedClient = clients.stream()
                .filter(cli -> cli.getUsername().equalsIgnoreCase(client.getUsername()))
                .findFirst().orElseThrow();
        foundedClient.setPassword(client.getPassword());
        foundedClient.setEmail(client.getEmail());

        return ResponseEntity.ok(foundedClient);
    }

    @PostMapping
    public ResponseEntity<?> addClient(@RequestBody Client client) {
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
                .findFirst().orElseThrow();
        System.out.println("Client deleted: " + foundedClient.getUsername());
        clients.removeIf(client -> client.getUsername().equalsIgnoreCase(username));

        return ResponseEntity.noContent().build();
    }
}
