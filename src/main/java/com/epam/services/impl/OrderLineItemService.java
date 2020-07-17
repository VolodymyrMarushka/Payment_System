package com.epam.services.impl;

import com.epam.domain.OrderItem;
import com.epam.services.CRUDService;

public class OrderLineItemService implements CRUDService<OrderItem> {


    @Override
    public OrderItem save(OrderItem orderLineItem) throws OrderLineItemServiceExeption {

        if (orderLineItem == null) {
            throw new OrderLineItemServiceExeption("There is nothing to save.");
        }

        if (orderLineItem.getProduct() == null
                || orderLineItem.getOrderId() <= 0) {
            throw new OrderLineItemServiceExeption("One of the product fields is empty or invalid data.");
        }

        return null;
    }

    @Override
    public OrderItem findById(int id) {
        return null;
    }

    @Override
    public OrderItem findByKey(String key) {
        return null;
    }

    @Override
    public boolean update(int id, OrderItem object) {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    public static class OrderLineItemServiceExeption extends RuntimeException {

        public OrderLineItemServiceExeption(String message) {
            super();
        }
    }
}
