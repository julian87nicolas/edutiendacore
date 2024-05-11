package edu.tienda.core.persistance.entities;

import edu.tienda.core.domain.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "favlist")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer id;
    String name;
    String tag;
    String username;
    Date creationDate;
    Date lastUpdateDate;

    @OneToMany
    List<ProductEntity> productList;

    public FavListEntity(String name, String tag, String username) {
        this.name = name;
        this.tag = tag;
        this.username = username;
        creationDate = new Date();
        lastUpdateDate = new Date();
        productList = new ArrayList<>();
    }

    public void addProductToList(ProductEntity product) {
        productList.add(product);
    }

    public void removeProductFromList(ProductEntity product) {
        productList.remove(product);
    }
}
