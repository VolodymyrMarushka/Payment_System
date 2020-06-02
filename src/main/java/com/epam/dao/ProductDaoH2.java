package com.epam.dao;

import com.epam.domain.Category;
import com.epam.domain.Product;
import com.epam.domain.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoH2 extends AbstractDao<Product, String> {


    @Override
    protected String getCreateQuery() {
        return "insert into `product` (name,price,produced,expiration,supplier_id, category_id) VALUES (?,?,?,?,?,?)";
    }

    @Override
    protected void setStatementForCreate(PreparedStatement pstm, Product someObject) {

        try {
            pstm.setString(1, someObject.getName());
            pstm.setDouble(2, someObject.getPrice());
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
// todo Format
        try {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString("Name"));
                product.setPrice(rs.getDouble("price"));
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
        return "Select * from `product` where `id` = ?";
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
        return "Select * from `product` where `name` = ? ";
    }

    @Override
    protected void setStatementForUpdate(PreparedStatement pstm, Product object) {
        try {
            pstm.setString(1, object.getName());
            pstm.setDouble(2, object.getPrice());
            pstm.setTimestamp(3, Timestamp.valueOf(object.getProduced()));
            pstm.setTimestamp(4, Timestamp.valueOf(object.getExpiration()));
            pstm.setObject(5, object.getSupplier());
            pstm.setObject(6, object.getCategory());
            pstm.setInt(7,object.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getUpdateQuery() {
        return "Update `product` set `name` = ?, price = ?, produced = ?, expiration = ?, supplier = ?, category = ? where `id` = ?";
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
        return "Delete from `product` where `id` = ? ";
    }

    @Override
    protected String getQueryForGetAll() {
        return "Select * from `product` ";
    }

    @Override
    protected String getDeleteAllQuery() {
        return "Delete  from `product`";
    }

    @Override
    protected String getTableName() {
        return "product";
    }

}
