package edu.tienda.core.services.product;

import edu.tienda.core.domain.Product;
import edu.tienda.core.persistance.entities.ProductEntity;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public void addProduct(Product product);
    public void deleteProduct(Integer id);
    public ProductEntity updateProduct(Product product);
    public List<Product> getProductsByPriceLessThan(Double price);
    public List<Product> getProductsByTitleLike(String name);
    public List<Product> getProductsByDescriptionLikeAndPriceGreaterThan(String description, Double price);

}
