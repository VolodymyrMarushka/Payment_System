package com.epam.dao;

import com.epam.domain.OrderLineItem;
import com.epam.domain.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderLineItemDaoH2 extends AbstractDao<OrderLineItem, Integer> {

    @Override
    protected String getCreateQuery() {
        return "Insert into `orderLineItem` (amount, order_id, product_id) values (?,?,?)";
    }

    @Override
    protected void setStatementForCreate(PreparedStatement pstm, OrderLineItem someObject) {
        try {
            pstm.setInt(1, someObject.getAmount());
            pstm.setInt(2, someObject.getOrderId());
            pstm.setInt(3, someObject.getProduct().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<OrderLineItem> parseResultSet(ResultSet rs) {

        List<OrderLineItem> orderLineItemList = new ArrayList<>();
        try {
            while (rs.next()) {
                OrderLineItem orderLineItem = new OrderLineItem();

                orderLineItem.setAmount(rs.getInt(2));
                orderLineItem.setId(rs.getInt(1));
                orderLineItem.setOrderId(rs.getInt("order_id"));
                orderLineItem.setProduct(Product.builder().id(rs.getInt(4)).build());

                orderLineItemList.add(orderLineItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orderLineItemList;
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
        return "Select *  from `orderLineItem` where id = ?";
    }

    @Override
    protected void setStatementForKey(PreparedStatement pstm, Integer key) {
        try {
            pstm.setInt(1, key);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getSelectByKeyQuery() {
        return "select * from `orderLineItem` where `order_id` = ?";
    }

    @Override
    protected void setStatementForUpdate(PreparedStatement pstm, OrderLineItem object) {

        try {
            pstm.setInt(1, object.getAmount());
            pstm.setInt(2, object.getOrderId());
            pstm.setObject(3, object.getProduct().getId());
            pstm.setInt(4, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getUpdateQuery() {
        return "Update `orderLineItem` set `amount` = ?, order_id = ?, product_id = ? where `id` = ?";
    }

    @Override
    protected void setStatementForDelete(PreparedStatement pstm, OrderLineItem object) {
        try {
            pstm.setInt(1, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getDeleteQuery() {
        return "Delete from `orderLineItem` where `id` = ? ";
    }

    @Override
    protected String getQueryForGetAll() {
        return "select * from `orderLineItem`";
    }

    @Override
    protected String getDeleteAllQuery() {
        return "delete from `orderLineItem`";
    }

    @Override
    protected String getTableName() {
        return "orderLineItem";
    }

}
