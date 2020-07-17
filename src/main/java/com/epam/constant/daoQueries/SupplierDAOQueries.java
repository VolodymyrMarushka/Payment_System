package com.epam.constant.daoQueries;

public class SupplierDAOQueries {

    public static final String INSERT = "INSERT INTO `SUPPLIER` (companyName) values (?)";
    public static final String SELECT_BY_ID = "select * from `supplier` where id = ?";
    public static final String SELECT_BY_KEY = "select * from `supplier` where `companyName` = ?";
    public static final String UPDATE = "Update `supplier` set companyName = ? where id = ?";
    public static final String DELETE_BY_ID = "delete from `supplier` where id = ?";
    public static final String SELECT_ALL = "Select * from supplier ";
    public static final String DELETE_ALL = "Delete from `supplier` ";
    public static final String TABLE_NAME = "Supplier";

    private SupplierDAOQueries() {
    }
}
