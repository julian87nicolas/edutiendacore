package edu.tienda.core.services;

import edu.tienda.core.domain.Product;
import edu.tienda.core.persistance.entities.ProductEntity;
import edu.tienda.core.persistance.repositories.ProductsRepository;
import edu.tienda.core.services.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductServiceDBImplTest {

    @Autowired
    private ProductService productService;

    @MockBean
    private ProductsRepository productsRepository;

    List<ProductEntity> productEntities;
    List<Product> products;

    @BeforeEach
    public void init() {
        productEntities = new ArrayList<>();
        productEntities.add(new ProductEntity(1, "Product 1", "Description 1", 100.0));
        productEntities.add(new ProductEntity(2, "Product 2", "Description 2", 200.0));
        productEntities.add(new ProductEntity(3, "Product 3", "Description 3", 300.0));

        products = new ArrayList<>();
        products.add(new Product(1, "Product 1", "Description 1", 100.0));
        products.add(new Product(2, "Product 2", "Description 2", 200.0));
        products.add(new Product(3, "Product 3", "Description 3", 300.0));
    }

    @Test
    public void getProductsTest() {
        Mockito.when(productsRepository.findAll()).thenReturn(productEntities);
        List<Product> result = productService.getProducts();
        assertEquals(products, result);
    }

    @Test
    public void addProductTest() {
        ProductEntity addedProduct = new ProductEntity(4, "Product 4", "Description 4", 400.0);
        Mockito.when(productsRepository.save(ArgumentMatchers.any())).then(invocation -> {
            productEntities.add(addedProduct);
            return addedProduct;
        });

        productService.addProduct(new Product(4, "Product 4", "Description 4", 400.0));
        assertEquals(productEntities.size(), 4);
    }
}
