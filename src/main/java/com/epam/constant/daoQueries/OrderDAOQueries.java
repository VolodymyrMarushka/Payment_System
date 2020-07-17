package com.epam.constant.daoQueries;

public class OrderDAOQueries {

    public static final String INSERT = "Insert into `orders` ( address, ordered, shipped, delivered, orderStatus) values (?,?,?,?,?)";
    public static final String SELECT_BY_ID = "Select * from `orders` where `id` = ?";
    public static final String SELECT_BY_KEY = "Select * from `orders` where `address` = ? ";
    public static final String UPDATE = "Update `orders` set `address` = ?, ordered = ?, shipped = ?, delivered = ?, orderStatus = ? where `id` = ?";
    public static final String DELETE_BY_ID = "Delete from `orders` where `id` = ? ";
    public static final String SELECT_ALL = "Select * from `orders` ";
    public static final String DELETE_ALL = "Delete  from `orders`";
    public static final String TABLE_NAME = "Orders";

    private OrderDAOQueries() {
    }
}
