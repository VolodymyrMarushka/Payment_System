package com.epam.services;

import com.epam.domain.Category;
import com.epam.domain.Product;
import com.epam.domain.Supplier;
import com.epam.services.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private ProductServiceImpl productService = new ProductServiceImpl();

    @Test
    void addProductTest() {
        Supplier supplier = Supplier.builder().companyName("Nestle").build();
        Category category = Category.builder().name("Sweets").build();
        Product product = Product.builder()
                .category(category)
                .supplier(supplier)
                .produced(LocalDateTime.now().minusMonths(3))
                .expiration(LocalDateTime.now().plusMonths(6))
                .name("Chocolate")
                .price(25).build();
       Product returned =  productService.save(product);

       assertEquals(product,returned);
    }

    @Test
    void getProductById() {
    }
}