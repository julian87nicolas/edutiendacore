package edu.tienda.core.persistance.repositories;

import edu.tienda.core.persistance.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    
}
