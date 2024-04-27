package edu.tienda.core.persistance.repositories;

import edu.tienda.core.persistance.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<ProductEntity, Integer> {
}
