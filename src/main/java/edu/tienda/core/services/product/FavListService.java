package edu.tienda.core.services.product;

import edu.tienda.core.domain.FavList;
import edu.tienda.core.persistance.entities.FavListEntity;
import edu.tienda.core.persistance.entities.ProductEntity;

import java.util.List;

public interface FavListService {
    FavListEntity create(FavList favList);
    FavListEntity read(Integer id, String username);
    FavListEntity update(FavList favList);
    void delete(Integer id);
    List<FavListEntity> readAll(String username);
    void addProductToList(String username, Integer listId, Integer productId);
    void removeProductFromList(String username, String listName, ProductEntity product);
}
