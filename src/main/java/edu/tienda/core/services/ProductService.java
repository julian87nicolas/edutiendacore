package edu.tienda.core.services;

import edu.tienda.core.domain.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getProducts();
    public void addProduct(Product product);
    public void deleteProduct(Integer id);
    public void updateProduct(Product product);
}
