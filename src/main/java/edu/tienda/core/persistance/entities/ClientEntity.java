package edu.tienda.core.persistance.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name="clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {
    @Id
    String username;
    String password;
    String email;

    @OneToMany
    List<ProductEntity> historicalPurchases;

    public ClientEntity(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.historicalPurchases = new ArrayList<>();
    }

    public void addProductToHistoricalPurchases(ProductEntity product) {
        this.historicalPurchases.add(product);
    }
}
