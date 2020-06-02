package com.epam.dao;

import com.epam.domain.Category;
import com.epam.domain.Product;
import com.epam.domain.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductDaoH2Test {

    private DAO<Product, String> productDAO;
    private String productName;
    private String updatedName;
    private double price;
    private double updatedPrice;
    private LocalDateTime produced;
    private LocalDateTime expiration;
    private Supplier supplier;
    private Supplier updatedSuplier;
    private Category category;

    @BeforeEach
    public void init() {
        productDAO = new ProductDaoH2();
        productName = "Test Product";
        price = 10.5;
        produced = LocalDateTime.of(2020, 05, 18, 6, 30);
        expiration = produced.plusMonths(5);
        supplier = new Supplier(1, "Some Supplier");
        category = new Category(1, "Some category");
    }

    @Test
    @Order(0)
    public void DELETE_ALL() {
        boolean isDeleted = productDAO.deleteAll();
        assertEquals(true, isDeleted);
    }

    @Test
    @Order(1)
    public void CREATE_NONE_EXIST_PRODUCT() {
        // todo cannot create product without category and supplier
        Product product = new Product(0, productName, price, produced, expiration, supplier, category);
        Product returned = productDAO.create(product);
        assertNotEquals(0, returned.getId());

    }

    @Test
    @Order(2)
    public void CREATE_EXIST_PRODUCT() {
        Product product = new Product(0, productName, price, produced, expiration, supplier, category);
        assertThrows(DaoException.class, () -> {
            productDAO.create(product);
        });
    }

    @Test
    @Order(3)
    public void READ_BY_EXIST_ID() {
        int id = 6;
        Product returned = productDAO.readById(id);
        assertEquals(id, returned.getId());
        assertEquals(productName, returned.getName());
        assertEquals(price, returned.getPrice());
        assertEquals(produced, returned.getProduced());
        assertEquals(expiration, returned.getExpiration());
        assertEquals(supplier.getId(), returned.getSupplier().getId());
        assertEquals(category.getId(), returned.getCategory().getId());
    }

    @Test
    @Order(4)
    public void READ_BY_NONE_EXIST_ID() {
        int id = 0;
        assertThrows(DaoException.class, () -> {
            productDAO.readById(id);
        });
    }

    @Test
    @Order(5)
    public void READ_BY_EXIST_KEY() {
        Product returned = productDAO.readByKey(productName);
        assertEquals(productName, returned.getName());
    }

    @Test
    @Order(6)
    public void READ_BY_NONO_EXIST_KEY() {
        String str = "NoneExistKey";
        assertThrows(DaoException.class, () -> {
            productDAO.readByKey(str);
        });
    }

    @Test
    @Order(7)
    public void UPDATE_EXIST_OBJECT() {
        Product product = productDAO.readByKey(productName);
        product.setName("Updated Product");
        product.setPrice(20);
        product.setSupplier(new Supplier(1, "Another Supplier"));

        productDAO.update(product);

        Product returned = productDAO.readByKey("Updated Product");
        updatedName = returned.getName();
        updatedPrice = returned.getPrice();
        updatedSuplier = returned.getSupplier();

        assertNotNull(returned);
        assertEquals(product.getName(), returned.getName());
        assertEquals(product.getPrice(), returned.getPrice());
        assertEquals(product.getSupplier(), returned.getSupplier());
    }

    @Test
    @Order(8)
    public void UPDATE_NONE_EXIST_OBJECT(){
        Product product = new Product(0, productName, price, produced, expiration, supplier, category);
        boolean isUpdated = productDAO.update(product);
        assertEquals(false,isUpdated);
    }

    @Test
    @Order(9)
    public void DELETE_EXIST_OBJECT(){
      Product product = new Product(1,updatedName,updatedPrice,produced,expiration,updatedSuplier,category);
      assertEquals(true, productDAO.delete(product));
    }

    @Test
    @Order(10)
    public void DELETE_NONE_EXIST_OBJECT(){
        Product product = new Product();
        assertEquals(false,productDAO.delete(product));
    }

    @Test
    @Order(11)
    public void GET_ALL(){
        List<Product> products = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            products.add(new Product(i, productName + i , price+i, produced.plusMinutes(i),expiration.plusMinutes(i)
            ,supplier,category));

            productDAO.create(new Product(i, productName + i , price+i, produced.plusMinutes(i),expiration.plusMinutes(i)
                    ,supplier,category));
        }
        assertEquals(products,productDAO.getAll());
    }

}
