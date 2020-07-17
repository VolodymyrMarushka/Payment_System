package com.epam.dao;

import com.epam.domain.Order;
import com.epam.domain.OrderStatus;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static com.epam.constant.daoQueries.OrderDAOQueries.*;

public class OrderDaoH2 extends AbstractDao<Order,String> {
    @Override
    protected String getCreateQuery() {
        return INSERT ;
    }

    @Override
    protected void setStatementForCreate(PreparedStatement pstm, Order someObject) {

        try {
            pstm.setString(1,someObject.getAddress());
            pstm.setTimestamp(2, Timestamp.valueOf(someObject.getOrdered()));
            pstm.setTimestamp(3,Timestamp.valueOf(someObject.getShipped()));
            pstm.setTimestamp(4,Timestamp.valueOf(someObject.getDelivered()));
            pstm.setString(5,someObject.getStatus().toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<Order> parseResultSet(ResultSet rs) {

        List<Order> orders = new ArrayList<>();

        try {
            while (rs.next()){
                Order order = new Order();
                order.setId(rs.getInt(1));
                order.setAddress(rs.getString(2));
                order.setOrdered(rs.getTimestamp(3).toLocalDateTime());
                order.setShipped(rs.getTimestamp(4).toLocalDateTime());
                order.setDelivered(rs.getTimestamp(5).toLocalDateTime());
                order.setStatus(OrderStatus.valueOf(rs.getString(6).toUpperCase()));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    protected void setStatementForId(PreparedStatement pstm, int id) {

        try {
            pstm.setInt(1,id);
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
            pstm.setString(1,key);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getSelectByKeyQuery() {
        return SELECT_BY_KEY;
    }

    @Override
    protected void setStatementForUpdate(PreparedStatement pstm, Order object) {
        try {
            pstm.setString(1,object.getAddress());
            pstm.setTimestamp(2,Timestamp.valueOf(object.getOrdered()));
            pstm.setTimestamp(3,Timestamp.valueOf(object.getShipped()));
            pstm.setTimestamp(4,Timestamp.valueOf(object.getDelivered()));
            pstm.setString(5,object.getStatus().toString());
            pstm.setInt(6,object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE;
    }

    @Override
    protected void setStatementForDelete(PreparedStatement pstm, Order object) {
        try {
            pstm.setInt(1,object.getId());
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
