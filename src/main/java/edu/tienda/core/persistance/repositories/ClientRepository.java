package edu.tienda.core.persistance.repositories;

import edu.tienda.core.domain.Client;
import edu.tienda.core.persistance.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, String>{
    Client findByUsername(String username);
}
