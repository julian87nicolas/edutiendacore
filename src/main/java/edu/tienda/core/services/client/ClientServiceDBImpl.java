package edu.tienda.core.services.client;

import edu.tienda.core.domain.Client;
import edu.tienda.core.persistance.entities.ClientEntity;
import edu.tienda.core.persistance.entities.ProductEntity;
import edu.tienda.core.persistance.repositories.ClientRepository;
import edu.tienda.core.persistance.repositories.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("clientDB")
@ConditionalOnProperty(
        value="product.strategy",
        havingValue = "ON_DB")
public class ClientServiceDBImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    ProductsRepository productsRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll()
                .stream().map(clientEntity -> new Client(
                        clientEntity.getUsername(),
                        clientEntity.getPassword(),
                        clientEntity.getEmail()
                )).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public void addClient(Client client) {
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save( new ClientEntity(
                client.getUsername(),
                client.getPassword(),
                client.getEmail()));
    }

    @Override
    public void deleteClient(String username) {
        clientRepository.deleteById(username);
    }

    @Override
    public ClientEntity updateClient(Client client) {
        ClientEntity updatedClient = clientRepository.findById(client.getUsername())
                .stream().map(clientEntity -> new ClientEntity(
                        client.getEmail() != null ? client.getEmail() : clientEntity.getUsername(),
                        client.getPassword() != null ? client.getPassword() : clientEntity.getPassword(),
                        client.getEmail() != null ? client.getEmail() : clientEntity.getEmail()
                )).findFirst().orElseThrow( () -> new RuntimeException("Client not found"));

        clientRepository.save(updatedClient);
        return updatedClient;
    }

    @Override
    public void addPurchaseToClient(String username, Integer productId) {
        ClientEntity clientEntity = clientRepository.findById(username)
                .orElseThrow( () -> new RuntimeException("Client not found"));
        ProductEntity productEntity = productsRepository.findById(productId)
                .orElseThrow( () -> new RuntimeException("Product not found"));

        clientEntity.addProductToHistoricalPurchases(productEntity);
        clientRepository.save(clientEntity);
    }
}
