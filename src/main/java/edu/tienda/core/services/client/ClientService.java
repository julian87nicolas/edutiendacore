package edu.tienda.core.services.client;

import edu.tienda.core.domain.Client;
import edu.tienda.core.persistance.entities.ClientEntity;

import java.util.List;

public interface ClientService {
    public List<Client> getClients();
    public void addClient(Client client);
    public void deleteClient(String username);
    public ClientEntity updateClient(Client client);
    public void addPurchaseToClient(String username, Integer productId);
}
