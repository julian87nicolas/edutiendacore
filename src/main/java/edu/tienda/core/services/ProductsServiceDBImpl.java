package edu.tienda.core.services;

import edu.tienda.core.domain.Product;
import edu.tienda.core.persistance.entities.ProductEntity;
import edu.tienda.core.persistance.repositories.ProductsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service("DB")
@ConditionalOnProperty(
        value="product.strategy",
        havingValue = "ON_DB")
public class ProductsServiceDBImpl implements ProductService {

    @Autowired
    private ProductsRepository productsRepository;

    public ProductsServiceDBImpl() {
        log.info("An instance of ProductServiceImpl is creating");
    }

    @Override
    public List<Product> getProducts() {
        return productsRepository.findAll()
                .stream().map(productEntity -> new Product(
                        productEntity.getId(),
                        productEntity.getTitle(),
                        productEntity.getDescription(),
                        productEntity.getPrice()
                )).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }

    @Override
    public void addProduct(Product product) {
        productsRepository.save( new ProductEntity(product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice()));
    }

    @Override
    public void deleteProduct(Integer id) {
        productsRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Product product) {
        Product foundedProduct = productsRepository.findById(product.getId())
                .stream().map(productEntity -> new Product(
                        productEntity.getId(),
                        productEntity.getTitle(),
                        productEntity.getDescription(),
                        productEntity.getPrice()
                )).findFirst().orElseThrow( () -> new RuntimeException("Product not found"));
    }
}
