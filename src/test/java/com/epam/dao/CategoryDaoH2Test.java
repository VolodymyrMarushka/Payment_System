package com.epam.dao;

import com.epam.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryDaoH2Test {
    private DAO<Category, String> categoryDAO;
    private String categoryName;
    private String updatedName;


    @BeforeEach
    public void init() {
        categoryDAO = new CategoryDaoH2();
        categoryName = "Test Category";
    }

    @Test
    @Order(0)
    public void DELETE_ALL() {
        boolean isDeleted = categoryDAO.deleteAll();
        assertEquals(true, isDeleted);
    }

    @Test
    @Order(1)
    public void CREATE_NONE_EXIST_CATEGORY() {
        Category category = new Category(categoryName);
        Category returned = categoryDAO.create(category);
        assertNotEquals(0, returned.getId());
        assertEquals(categoryName, returned.getName());
    }


    @Test
    @Order(2)
    public void CREATE_EXIST_CATEGORY() {
        Category category = new Category(categoryName);
        assertThrows(DaoException.class, () -> {
            categoryDAO.create(category);
        });
    }

    @Test
    @Order(3)
    public void readByExistId() {
        int id = 1;
        Category returned = categoryDAO.readById(id);
        assertEquals(id, returned.getId());
        assertEquals(categoryName, returned.getName());
    }

    @Test
    @Order(4)
    public void readByNoneExist_Id() {
        int id = 0;
        assertThrows(DaoException.class, () -> {
            categoryDAO.readById(id);
        });
    }

    @Test
    @Order(5)
    public void readByExistKey() {
        Category returned = categoryDAO.readByKey(categoryName);
        assertEquals(categoryName, returned.getName());
    }

    @Test
    @Order(6)
    public void readByNoneExistKey() {
        String str = "NoneExistKey";
        assertThrows(DaoException.class, () -> {
            categoryDAO.readByKey(str);
        });
    }

    @Test
    @Order(7)
    public void updateExistObject() {
        Category category = categoryDAO.readByKey(categoryName);
        category.setName("UpdateCategory");

        categoryDAO.update(category);

        Category returned = categoryDAO.readByKey("UpdateCategory");

        updatedName = returned.getName();
        assertNotNull(returned);
        assertEquals(category.getName(), returned.getName());
    }

    @Test
    @Order(8)
    public void updateNoneExistObject() {
        Category category = new Category(0, categoryName);
        boolean isUpdated = categoryDAO.update(category);
        assertEquals(false, isUpdated);
    }

    @Test
    @Order(9)
    public void deleteExistObject() {
        Category category = new Category(6, updatedName);
        assertEquals(true, categoryDAO.delete(category));
    }

    @Test
    @Order(10)
    public void deleteNoneExistObject() {
        Category category = new Category(0, "None Exist Name");
        assertEquals(false, categoryDAO.delete(category));
    }



    @Test
    @Order(11)
    public void getAll() {
        DELETE_ALL();
        List<Category> categories = new ArrayList<>();
        String category = "Category ";
        for (int i = 11; i < 15; i++) {
            categories.add(new Category(i, category + i));
            categoryDAO.create(new Category(i, category + i));
        }
        assertEquals(categories, categoryDAO.getAll());
    }
}