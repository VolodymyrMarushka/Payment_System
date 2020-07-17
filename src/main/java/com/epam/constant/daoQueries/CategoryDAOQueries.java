package com.epam.constant.daoQueries;

public class CategoryDAOQueries {

    public static final String INSERT = "Insert into `category` (name) values (?)";
    public static final String SELECT_BY_ID = "Select *  from category where id = ?";
    public static final String SELECT_BY_KEY = "Select * from `category` where `name` = ?";
    public static final String UPDATE = "Update category set name = ? where id = ?";
    public static final String DELETE_BY_ID = "Delete from category where id = ?";
    public static final String SELECT_ALL = "Select * from `category` ";
    public static final String DELETE_ALL = "Delete from `category` ";
    public static final String TABLE_NAME = "category";

    private CategoryDAOQueries() {
    }
}
