package com.epam.services.impl;

import com.epam.dao.CategoryDaoH2;
import com.epam.dao.DAO;
import com.epam.dao.ProductDaoH2;
import com.epam.dao.SupplierDaoH2;
import com.epam.domain.Category;
import com.epam.domain.Product;
import com.epam.domain.Supplier;
import com.epam.exception.CategoryServiceException;
import com.epam.exception.ProductServiceException;
import com.epam.services.CRUDService;
import org.apache.log4j.Logger;

public class ProductServiceImpl implements CRUDService<Product> {

    private static Logger logger = Logger.getLogger(ProductServiceImpl.class);
    private DAO<Product, String> productDAO = new ProductDaoH2();
    private DAO<Supplier, String> supplierDAO = new SupplierDaoH2();
    private DAO<Category, String> categoryDAO = new CategoryDaoH2();


    public Product save(Product someProduct) throws ProductServiceException {

        if (someProduct == null) {
            throw new ProductServiceException("There is no product to save.");
        }

        if (someProduct.getName() == null
                || someProduct.getPrice() <= 0
                || someProduct.getCategory() == null
                || someProduct.getExpiration() == null
                || someProduct.getProduced() == null
                || someProduct.getExpiration().isBefore(someProduct.getProduced())
                || someProduct.getSupplier() == null) {
            throw new ProductServiceException("One of the product fields are null");
        }

        if (someProduct.getSupplier().getId() == 0) {
            Supplier supplier = supplierDAO.readByKey(someProduct.getSupplier().getCompanyName());
            if (supplier == null) {
                supplier = supplierDAO.create(someProduct.getSupplier());
            }
            someProduct.setSupplier(supplier);
        }

        if (someProduct.getCategory().getId() == 0) {
            Category category = categoryDAO.readByKey(someProduct.getCategory().getName());
            if (category == null) {
                category = categoryDAO.create(someProduct.getCategory());
            }
            someProduct.setCategory(category);
        }

        Product returned = productDAO.create(someProduct);

        if (returned == null || returned.getId() == 0) {
            throw new ProductServiceException("Problem with saving product");

        }
        return returned;
    }

    public Product findById(int id) {

        if (id <= 1) {
            throw new ProductServiceException("Product identifier must be positive and not equal 0.");
        }

        Product product = productDAO.readById(id);

        if (product == null) {
            throw new ProductServiceException("Product could not be received by id.");
        }
        return product;
    }

    public Product findByKey(String key) {
        if (key == null) {
            throw new ProductServiceException("Product name cannot be empty");
        }

        Product product = productDAO.readByKey(key);

        if (product == null) {
            throw new ProductServiceException("There is no product by this name");
        }
        return product;
    }

    @Override
    public boolean update(int id, Product product) {
        if (product == null) {
            throw new ProductServiceException("There is not product to update");
        }

        if (product.getId() <= 0
                || product.getName() == null
                || product.getPrice() <= 0
                || product.getCategory() == null
                || product.getProduced() == null
                || product.getExpiration() == null
                || product.getExpiration().isBefore(product.getProduced())
                || product.getSupplier() == null) {
            throw new ProductServiceException("One of the product fields is empty or invalid data.");
        }
        return productDAO.update(product);
    }

    public boolean deleteById(int id) {

        if (id <= 0) {
            throw new ProductServiceException("Product id must be positive");
        }
        Product returned = productDAO.readById(id);
        if (returned == null) {
            throw new CategoryServiceException("There is not product with current id!");
        }

        return productDAO.delete(returned);
    }

}
