package edu.tienda.core.services.client;

import edu.tienda.core.domain.Client;

import java.util.List;

public interface ClientService {
    public List<Client> getClients();
    public void addClient(Client client);
    public void deleteClient(String username);
    public void updateClient(Client client);
}
