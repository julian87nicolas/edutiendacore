package edu.tienda.core.services;

import edu.tienda.core.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Lazy
@Slf4j
@Service("MEMORY")
@ConditionalOnProperty(
        value = "product.strategy",
        havingValue = "ON_MEMORY")
public class ProductServiceImpl implements ProductService {
    private List<Product> products = new ArrayList<>(Arrays.asList(
            new Product(1, "Laptop", "Laptop HP", 1000.0),
            new Product(2, "Mouse", "Mouse Logitech", 20.0),
            new Product(3, "Keyboard", "Keyboard Logitech", 30.0)
    ));

    public ProductServiceImpl() {
        log.info("An instance of ProductServiceImpl is creating");
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void addProduct(Product product) {

    }

    @Override
    public void deleteProduct(Integer id) {

    }

    @Override
    public void updateProduct(Product product) {

    }
}
