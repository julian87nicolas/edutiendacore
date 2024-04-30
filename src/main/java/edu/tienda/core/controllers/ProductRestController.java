package edu.tienda.core.controllers;

import edu.tienda.core.configurations.ConfigurationParameters;
import edu.tienda.core.domain.Product;
import edu.tienda.core.services.product.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    @Lazy
    private ProductService productService;

    @GetMapping
    public ResponseEntity<?> getProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();

        return ResponseEntity.created(location).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
        if(product.getId().equals(null)) {
            product.setId(id);
        }
        productService.updateProduct(product);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/priceless")
    public ResponseEntity<?> getProductsByPriceLessThan(@RequestParam Double price) {
        List<Product> products = productService.getProductsByPriceLessThan(price);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/titlelike")
    public ResponseEntity<?> getProductsByTitleLike(@RequestParam String title) {
        List<Product> products = productService.getProductsByTitleLike(title);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/descandprice")
    public ResponseEntity<?> getProductsByDescriptionLikeAndPriceGreaterThan(@RequestParam String description, @RequestParam Double price) {
        List<Product> products = productService.getProductsByDescriptionLikeAndPriceGreaterThan(description, price);
        return ResponseEntity.ok(products);
    }
}
