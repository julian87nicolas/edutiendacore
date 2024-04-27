package edu.tienda.core.services.product;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tienda.core.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Primary
@Service("JSON")
@ConditionalOnProperty(
        value = "product.strategy",
        havingValue = "ON_JSON")
public class ProductServiceJsonImpl implements ProductService {

    @Override
    public List<Product> getProducts() {
        List<Product> products;
        try {
            products = new ObjectMapper().readValue(this.getClass().getResourceAsStream("/products.json"),
                                                    new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
