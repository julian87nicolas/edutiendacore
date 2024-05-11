package edu.tienda.core.services.product;

import edu.tienda.core.domain.Product;
import edu.tienda.core.persistance.entities.ProductEntity;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
    void addProduct(Product product);
    void deleteProduct(Integer id);
    ProductEntity updateProduct(Product product);
    List<Product> getProductsByPriceLessThan(Double price);
    List<Product> getProductsByTitleLike(String name);
    List<Product> getProductsByDescriptionLikeAndPriceGreaterThan(String description, Double price);

}
