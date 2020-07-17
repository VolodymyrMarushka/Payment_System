package com.epam.constant.daoQueries;

public class ProductDAOQueries {

    public static final String INSERT = "insert into `product` (name,price,produced,expiration,supplier_id, category_id) VALUES (?,?,?,?,?,?)";
    public static final String SELECT_BY_ID = "Select * from `product` where `id` = ?";
    public static final String SELECT_BY_KEY = "Select * from `product` where `name` = ? ";
    public static final String UPDATE = "Update `product` set `name` = ?, price = ?, produced = ?, expiration = ?, supplier_id = ?, category_id = ? where `id` = ?";
    public static final String DELETE_BY_ID = "Delete from `product` where `id` = ? ";
    public static final String SELECT_ALL = "Select * from `product` ";
    public static final String DELETE_ALL = "Delete  from `product`";
    public static final String TABLE_NAME = "product";

    private ProductDAOQueries() {
    }
}
