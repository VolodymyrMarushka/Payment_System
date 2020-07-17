package com.epam.services.impl;

import com.epam.dao.DAO;
import com.epam.dao.OrderDaoH2;
import com.epam.dao.OrderLineItemDaoH2;
import com.epam.domain.Order;
import com.epam.domain.OrderItem;
import com.epam.exception.CategoryServiceException;
import com.epam.exception.OrderServiceException;
import com.epam.services.CRUDService;
import org.apache.log4j.Logger;

public class OrderServiceImpl implements CRUDService<Order> {

    private static Logger logger = Logger.getLogger(OrderServiceImpl.class);
    private DAO<Order, String> orderDAO = new OrderDaoH2();
    private DAO<OrderItem,Integer> orderLineItemIntegerDAO = new OrderLineItemDaoH2();

    public Order save(Order someOrder) throws OrderServiceException {

        if (someOrder == null) {
            throw new OrderServiceException("There is no order! Add an order to save.");
        }
        if (someOrder.getAddress() == null
                || someOrder.getStatus() == null
                || someOrder.getOrdered() == null
                || someOrder.getShipped() == null
                || someOrder.getDelivered() == null) {
            throw new OrderServiceException("One of the Order fields is empty");
        }

        Order order = orderDAO.create(someOrder);
        if (order == null || order.getId() == 0) {
            throw new OrderServiceException("Problem with saving order");
        }
        return order;
    }

    //todo methods in OredrServiceImpl
    @Override
    public Order findById(int id) throws OrderServiceException {

            if (id <= 0) {
                throw new CategoryServiceException("Order id cannot be 0 or negative!");
            }

            Order returned = orderDAO.readById(id);

            if (returned == null) {
                throw new CategoryServiceException("There is not order with the current identifier");
            }
            return returned;
        }

    @Override
    public Order findByKey(String key)  throws OrderServiceException {
        if (key == null){
            throw new CategoryServiceException("Address of order can not be null!");
        }

        Order returned = orderDAO.readByKey(key);

        if (returned == null ){
            throw new CategoryServiceException("There is not order with current address!");
        }
        return returned;
    }

    @Override
    public boolean update(int id, Order order) throws OrderServiceException {

        if (id <= 0) {
            throw new CategoryServiceException("Order id cannot be 0 or negative!");
        }

        if (order == null) {
            throw new CategoryServiceException("There is no order!!! Please add a order to save.");
        }
        if (order.getAddress() == null) {
            throw new CategoryServiceException("Address of the order cannot be empty");
        }

        Order returned = orderDAO.readById(id);

        if (returned == null ){
            throw new CategoryServiceException("There is not order with current address!");
        }

        return orderDAO.update(order);
    }

    @Override
    public boolean deleteById(int id) throws OrderServiceException {

        if (id <= 0) {
            throw new CategoryServiceException("Category id cannot be 0 or negative!");
        }

        Order returned = orderDAO.readById(id);

        if (returned == null ){
            throw new CategoryServiceException("There is not order with current id!");
        }

        return orderDAO.delete(returned);
    }


}
