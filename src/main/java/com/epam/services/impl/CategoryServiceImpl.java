package com.epam.services.impl;

import com.epam.dao.CategoryDaoH2;
import com.epam.dao.DAO;
import com.epam.domain.Category;
import com.epam.exception.CategoryServiceException;
import com.epam.services.CRUDService;
import org.apache.log4j.Logger;


public class CategoryServiceImpl implements CRUDService<Category> {

    private static Logger loger = Logger.getLogger(CategoryServiceImpl.class);
    private DAO<Category, String> categoryDAO = new CategoryDaoH2();

    @Override
    public Category save(Category someCategory) throws CategoryServiceException {

        if (someCategory == null) {
            throw new CategoryServiceException("There is no category!!! Please add a category to save.");
        }
        if (someCategory.getName() == null) {
            throw new CategoryServiceException("Name of category cannot be empty");
        }

        Category returned = categoryDAO.create(someCategory);

        if (returned == null || returned.getId() == 0) {
            throw new CategoryServiceException("Problem with saving category");
        }
        return returned;
    }

    @Override
    public Category findById(int id) throws CategoryServiceException {

        if (id <= 0) {
            throw new CategoryServiceException("Category id cannot be 0 or negative!");
        }

        Category returned = categoryDAO.readById(id);

        if (returned == null) {
            throw new CategoryServiceException("There is not category with the current identifier");
        }
        return returned;
    }

    @Override
    public Category findByKey(String key) throws CategoryServiceException {

        if (key == null) {
            throw new CategoryServiceException("Category name can not be null!");
        }

        Category returned = categoryDAO.readByKey(key);

        if (returned == null || returned.getId() == 0) {
            throw new CategoryServiceException("There is not category with current name!");
        }

        return returned;
    }

    @Override
    public boolean update(int id, Category category) throws CategoryServiceException {
        if (id <= 0) {
            throw new CategoryServiceException("Category id cannot be 0 or negative!");
        }

        if (category == null) {
            throw new CategoryServiceException("There is no category!!! Please add a category to save.");
        }
        if (category.getName() == null) {
            throw new CategoryServiceException("Name of category cannot be empty");
        }

        Category returned = categoryDAO.readById(id);

        if (returned == null) {
            throw new CategoryServiceException("There is not category with current name!");
        }

        return categoryDAO.update(category);
    }

    @Override
    public boolean deleteById(int id) {
        if (id <= 0) {
            throw new CategoryServiceException("Category id cannot be 0 or negative!");
        }

        Category returned = categoryDAO.readById(id);

        if (returned == null ) {
            throw new CategoryServiceException("There is not category with current id!");
        }

        return categoryDAO.delete(returned);
    }

}
