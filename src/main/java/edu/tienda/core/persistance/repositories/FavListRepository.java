package edu.tienda.core.persistance.repositories;

import edu.tienda.core.persistance.entities.FavListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavListRepository extends JpaRepository<FavListEntity, Integer> {
    FavListEntity findFavListEntityById(Integer id);
    FavListEntity findFavListEntityByUsernameAndName(String username, String name);
    List<FavListEntity> findFavListEntitiesByUsername(String username);
}
