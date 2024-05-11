package edu.tienda.core.services.product;

import edu.tienda.core.domain.FavList;
import edu.tienda.core.domain.Product;
import edu.tienda.core.exceptions.ResourceNotFoundException;
import edu.tienda.core.persistance.entities.FavListEntity;
import edu.tienda.core.persistance.entities.ProductEntity;
import edu.tienda.core.persistance.repositories.FavListRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FavListServiceDBImpl implements FavListService {

    FavListRepository favListRepository;

    @Override
    public FavListEntity create(FavList favList) {
        FavListEntity favListEntity = new FavListEntity(
                favList.getName(),
                favList.getTag(),
                favList.getUsername()
        );

        favListRepository.save(favListEntity);
        return favListEntity;
    }

    @Override
    public FavListEntity read(Integer id) {
        return favListRepository.findFavListEntityById(id);
    }

    @Override
    public FavListEntity update(FavList favList) {
        FavListEntity favListEntity = favListRepository.findFavListEntityById(favList.getId());
        if (favListEntity == null) {
            throw new ResourceNotFoundException("List not founded.");
        }
        FavListEntity updatedFavList = new FavListEntity().builder()
                .name(favList.getName() != null ? favList.getName() : favListEntity.getName())
                .tag(favList.getTag() != null ? favList.getTag() : favListEntity.getTag())
                .build();

        favListRepository.save(updatedFavList);
        return updatedFavList;
    }

    @Override
    public void delete(Integer id) {
        FavListEntity favListEntity = favListRepository.findFavListEntityById(id);
        if (favListEntity == null) {
            throw new ResourceNotFoundException("List not founded.");
        }
        favListRepository.delete(
                favListRepository.findFavListEntityById(id)
        );
    }

    @Override
    public List<FavListEntity> readAll(String username) {
        return favListRepository.findFavListEntitiesByUsername(username);
    }

    @Override
    public void addProductToList(String username, String listName, ProductEntity product) {
        FavListEntity favListEntity = favListRepository.findFavListEntityByUsernameAndName(username, listName);
        if(favListEntity == null) {
            throw new ResourceNotFoundException("Fav list not founded");
        }
        favListEntity.addProductToList(product);
    }

    @Override
    public void removeProductFromList(String username, String listName, ProductEntity product) {
        FavListEntity favListEntity = favListRepository.findFavListEntityByUsernameAndName(username, listName);
        if(favListEntity == null) {
            throw new ResourceNotFoundException("Fav list not founded");
        }
        favListEntity.removeProductFromList(product);
    }
}
