package edu.tienda.core.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.tienda.core.domain.Product;
import edu.tienda.core.services.product.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductRestController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProductRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private Product product;
    private List<Product> products;

    @BeforeEach
    public void init() {
        product = new Product();
        product.setId(1);
        product.setTitle("Product 1");
        product.setDescription("Description 1");
        product.setPrice(100.0);

        products = new ArrayList<>();
        products.add(product);
        products.add(new Product(2, "Product 2", "Description 2", 200.0));
    }


    @Test
    public void addProductTest() throws Exception {
        doAnswer(invocation -> {
            products.add(new Product(3, "Product 3", "Description 3", 300.0));
            return null;
        }).when(productService).addProduct(ArgumentMatchers.any());

        ResultActions response = mockMvc.perform(post("/products")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(product)));

        response.andExpect(status().isCreated());
        assertEquals(3, products.size());
    }

    @Test
    public void getProductsTest() throws Exception {
        given(productService.getProducts()).willReturn(products);

        ResultActions response = mockMvc.perform(get("/products")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(products)));

        response.andExpect(status().isOk());
        assertEquals(response.andReturn().getResponse().getContentAsString(), objectMapper.writeValueAsString(products));
    }

    @Test
    public void updateProductTest() throws Exception {

        doAnswer(invocation -> {
            Product argument = product;
            product.setDescription("Description updated");
            return null;
        }).when(productService).updateProduct(ArgumentMatchers.any());

        ResultActions response = mockMvc.perform(put("/products/1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(product)));

        response.andExpect(status().isOk());
        assertEquals("Description updated", product.getDescription());
    }

    @Test
    public void getProductsByPriceLessThanTest() throws Exception {
        Double price = 150.0;
        given(productService.getProductsByPriceLessThan(price)).willReturn(List.of(product));

        ResultActions response = mockMvc.perform(get("/products/priceless?price=150.0")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(product)));

        response.andExpect(status().isOk());
    }

    @Test
    public void getProductsByTitleLikeTest() throws Exception {
        String title = "Product 1";
        given(productService.getProductsByTitleLike(title)).willReturn(List.of(product));

        ResultActions response = mockMvc.perform(get("/products/titlelike?title=Product 1")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(product)));

        response.andExpect(status().isOk());
    }

    @Test
    public void getProductsByDescriptionLikeAndPriceGreaterThanTest() throws Exception {
        String description = "Description 1";
        Double price = 50.0;
        given(productService.getProductsByDescriptionLikeAndPriceGreaterThan(description, price)).willReturn(List.of(product));

        ResultActions response = mockMvc.perform(get("/products/descandprice?description=Description 1&price=50.0")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(product)));

        response.andExpect(status().isOk());
    }

}

