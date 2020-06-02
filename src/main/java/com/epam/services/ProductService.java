package com.epam.services;

import com.epam.dao.DAO;
import com.epam.dao.ProductDaoH2;
import com.epam.domain.Product;
import com.epam.exception.ProductServiceException;
import org.apache.log4j.Logger;

public class ProductService {

    private static Logger logger = Logger.getLogger(ProductService.class);
    private DAO<Product, String> productDAO = new ProductDaoH2();
    private DAO<Product, String> supplierDAO = new ProductDaoH2();


    public Product addProduct(Product someProduct) throws ProductServiceException {

        if (someProduct == null) {
            throw new ProductServiceException("Null product can not be saved");
        }
        if (someProduct.getName() == null
                || someProduct.getPrice() == 0
                || someProduct.getCategory() == null
                || someProduct.getExpiration() == null
                || someProduct.getProduced() == null
                || someProduct.getSupplier() == null) {
            throw new ProductServiceException("One of the Product fields is null");
        }

        if (someProduct.getSupplier().getId() == 0) {
            throw new ProductServiceException("Product cannot be saved without Supplier");
        }
        
        if (someProduct.getCategory().getId() == 0) {
            throw new ProductServiceException("Product cannot be saved without Category");
        }

        Product returned = productDAO.create(someProduct);

        if (returned == null || returned.getId() == 0) {
            throw new ProductServiceException("Problem with saving product");

        }
        return returned;
    }
}
