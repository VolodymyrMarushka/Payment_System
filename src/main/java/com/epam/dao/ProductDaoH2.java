package com.epam.dao;

import com.epam.domain.Category;
import com.epam.domain.Product;
import com.epam.domain.Supplier;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.epam.constant.daoQueries.ProductDAOQueries.*;

public class ProductDaoH2 extends AbstractDao<Product, String> {


    @Override
    protected String getCreateQuery() {
        return INSERT;
    }

    @Override
    protected void setStatementForCreate(PreparedStatement pstm, Product someObject) {

        try {
            pstm.setString(1, someObject.getName());
            pstm.setBigDecimal(2, someObject.getPrice());
            pstm.setTimestamp(3, Timestamp.valueOf(someObject.getProduced()));
            pstm.setTimestamp(4, Timestamp.valueOf(someObject.getExpiration()));
            pstm.setInt(5, someObject.getSupplier().getId());
            pstm.setInt(6, someObject.getCategory().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected List<Product> parseResultSet(ResultSet rs) {
        List<Product> products = new ArrayList<>();

        try {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString("Name"));
                product.setPrice(BigDecimal.valueOf(rs.getDouble("price")));
                product.setProduced(rs.getTimestamp(4).toLocalDateTime());
                product.setExpiration(rs.getTimestamp(5).toLocalDateTime());
                product.setSupplier(Supplier.builder().id(rs.getInt(6)).build());
                product.setCategory(Category.builder().id(rs.getInt(7)).build());
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }


    @Override
    protected void setStatementForId(PreparedStatement pstm, int id) {

        try {
            pstm.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getSelectByIdQuery() {
        return SELECT_BY_ID;
    }

    @Override
    protected void setStatementForKey(PreparedStatement pstm, String key) {

        try {
            pstm.setString(1, key);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getSelectByKeyQuery() {
        return SELECT_BY_KEY;
    }

    @Override
    protected void setStatementForUpdate(PreparedStatement pstm, Product object) {
        try {
            pstm.setString(1, object.getName());
            pstm.setBigDecimal(2, object.getPrice());
            pstm.setTimestamp(3, Timestamp.valueOf(object.getProduced()));
            pstm.setTimestamp(4, Timestamp.valueOf(object.getExpiration()));
            pstm.setObject(5, object.getSupplier().getId());
            pstm.setObject(6, object.getCategory().getId());
            pstm.setInt(7, object.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE;
    }

    @Override
    protected void setStatementForDelete(PreparedStatement pstm, Product object) {
        try {
            pstm.setInt(1, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_BY_ID;
    }

    @Override
    protected String getQueryForGetAll() {
        return SELECT_ALL;
    }

    @Override
    protected String getDeleteAllQuery() {
        return DELETE_ALL;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

}
