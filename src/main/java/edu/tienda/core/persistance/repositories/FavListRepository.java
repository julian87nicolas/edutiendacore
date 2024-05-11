package edu.tienda.core.persistance.repositories;

import edu.tienda.core.persistance.entities.FavListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavListRepository extends JpaRepository<Integer, FavListEntity> {
}
