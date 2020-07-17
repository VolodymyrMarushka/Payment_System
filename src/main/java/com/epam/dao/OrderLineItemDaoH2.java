package com.epam.dao;

import com.epam.domain.OrderItem;
import com.epam.domain.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.constant.daoQueries.OrderLineItemDAOQueries.*;

public class OrderLineItemDaoH2 extends AbstractDao<OrderItem, Integer> {

    @Override
    protected String getCreateQuery() {
        return INSERT;
    }

    @Override
    protected void setStatementForCreate(PreparedStatement pstm, OrderItem someObject) {
        try {
            pstm.setInt(1, someObject.getAmount());
            pstm.setInt(2, someObject.getOrderId());
            pstm.setInt(3, someObject.getProduct().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<OrderItem> parseResultSet(ResultSet rs) {

        List<OrderItem> orderLineItemList = new ArrayList<>();
        try {
            while (rs.next()) {
                OrderItem orderLineItem = new OrderItem();

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
        return SELECT_BY_ID;
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
        return SELECT_BY_KEY;
    }

    @Override
    protected void setStatementForUpdate(PreparedStatement pstm, OrderItem object) {

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
        return UPDATE;
    }

    @Override
    protected void setStatementForDelete(PreparedStatement pstm, OrderItem object) {
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
