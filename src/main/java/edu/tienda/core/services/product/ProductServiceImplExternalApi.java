package edu.tienda.core.services.product;

import edu.tienda.core.domain.Product;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("API")
@ConditionalOnProperty(
        value = "product.strategy",
        havingValue = "ON_EXTERNAL_API"
)
public class ProductServiceImplExternalApi implements ProductService {
    private final String API_URL = "https://fakestoreapi.com/products";

    @Override
    public List<Product> getProducts() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<Product>> response = restTemplate
                .exchange(API_URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Product>>() {
                        });
        List<Product> products = response.getBody();

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
