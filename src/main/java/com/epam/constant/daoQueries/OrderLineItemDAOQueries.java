package com.epam.constant.daoQueries;

public class OrderLineItemDAOQueries {

    public static final String INSERT = "Insert into `orderLineItem` (amount, order_id, product_id) values (?,?,?)";
    public static final String SELECT_BY_ID = "Select *  from `orderLineItem` where id = ?";
    public static final String SELECT_BY_KEY = "select * from `orderLineItem` where `order_id` = ?";
    public static final String UPDATE = "Update `orderLineItem` set `amount` = ?, order_id = ?, product_id = ? where `id` = ?";
    public static final String DELETE_BY_ID = "Delete from `orderLineItem` where `id` = ? ";
    public static final String SELECT_ALL = "select * from `orderLineItem`";
    public static final String DELETE_ALL = "delete from `orderLineItem`";
    public static final String TABLE_NAME = "orderLineItem";

    private OrderLineItemDAOQueries() {
    }
}
