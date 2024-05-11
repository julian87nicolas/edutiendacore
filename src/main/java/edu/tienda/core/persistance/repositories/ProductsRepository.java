package edu.tienda.core.persistance.repositories;

import edu.tienda.core.persistance.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

@Repository
public interface ProductsRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByPriceLessThan(Double price);
    List<ProductEntity> findByTitleLike(String name);
    List<ProductEntity> findByDescriptionLikeAndPriceGreaterThan(String description, Double price);
    ProductEntity findProductEntityById(Integer id);
}
