package edu.tienda.core.services.client;

import edu.tienda.core.domain.Client;
import edu.tienda.core.persistance.entities.ClientEntity;
import edu.tienda.core.persistance.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
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
    public void updateClient(Client client) {
        Client foundedClient = clientRepository.findById(client.getUsername())
                .stream().map(clientEntity -> new Client(
                        clientEntity.getUsername(),
                        clientEntity.getPassword(),
                        clientEntity.getEmail()
                )).findFirst().orElseThrow( () -> new RuntimeException("Client not found"));
    }
}
