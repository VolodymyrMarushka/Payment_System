package com.epam.dao;


import com.epam.domain.Category;
import com.epam.domain.OrderLineItem;
import com.epam.domain.Product;
import com.epam.domain.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderLineItemDaoH2Test {

    private DAO<OrderLineItem, Integer> orderLineItemDAO;
    private int amount;
    private int updatedAmount;
    private int order_id;  // todo order_id unique
    private Product product;

    @BeforeEach
    public void init() {
        orderLineItemDAO = new OrderLineItemDaoH2();
        amount = 2;
        order_id = 1;
        LocalDateTime date = LocalDateTime.now();
        product = new Product(1, "Some product", 15, date, date.plusMonths(3), new Supplier(), new Category());
    }

    @Test
    @Order(0)
    public void DELETE_ALL() {
        boolean isDeleted = orderLineItemDAO.deleteAll();
        assertEquals(true, isDeleted);
    }

    @Test
    @Order(1)
    public void CREATE_NONE_EXIST_ITEM() {
        OrderLineItem item = new OrderLineItem(1, product, 2, 1);
        OrderLineItem returned = orderLineItemDAO.create(item);
        assertEquals(amount, returned.getAmount());
        assertNotEquals(0, returned.getId());
    }

    @Test
    @Order(2)
    public void CREATE_EXIST_ITEM() {
        OrderLineItem item = new OrderLineItem(1, product, 2, 1);
        orderLineItemDAO.create(item);
    }

    @Test
    @Order(3)
    public void READ_BY_EXIST_ID() {
        int id = 1;
        OrderLineItem returned = orderLineItemDAO.readById(id);
        assertEquals(id, returned.getId());
        assertEquals(amount, returned.getAmount());
    }

    @Test
    @Order(4)
    public void READ_BY_NONE_EXIST_ID() {
        int id = 0;
        assertThrows(DaoException.class, () -> {
            orderLineItemDAO.readById(id);
        });
    }

    @Test
    @Order(5)
    public void READ_BY_EXIST_KEY() {
        OrderLineItem returned = orderLineItemDAO.readByKey(order_id);
        assertEquals(order_id, returned.getOrderId());
        assertEquals(amount,returned.getAmount());
    }

    @Test
    @Order(6)
    public void READ_BY_NONE_EXIST_KEY() {
        int key = -1;
        assertThrows(DaoException.class, () -> {
            orderLineItemDAO.readByKey(key);
        });
    }

    @Test
    @Order(7)
    public void UPDATE_EXIST_OBJECT() {
        OrderLineItem item = orderLineItemDAO.readById(1);
        item.setAmount(5);     // todo how to set for instance new Supplier

        orderLineItemDAO.update(item);
        OrderLineItem returned = orderLineItemDAO.readByKey(1);
        updatedAmount = returned.getAmount();
        assertNotNull(returned);
        assertEquals(item.getAmount(), returned.getAmount());
    }

    @Test
    @Order(8)
    public void UPDATE_NONE_EXIST_OBJECT() {
        OrderLineItem item = new OrderLineItem(0,product,10,2);
        boolean isUpdated = orderLineItemDAO.update(item);
        assertEquals(false, isUpdated);
    }

    @Test
    @Order(9)
    public void DELETE_EXIST_OBJECT() {
        OrderLineItem item = orderLineItemDAO.readById(1);
        assertEquals(true, orderLineItemDAO.delete(item));
    }

    @Test
    @Order(10)
    public void DELETE_NONE_EXIST_OBJECT() {
        OrderLineItem item = new OrderLineItem(0,product,15,3);
        assertEquals(false, orderLineItemDAO.delete(item));
    }

    @Test
    @Order(11)
        public void GET_ALL() {
        DELETE_ALL();
        List<OrderLineItem> items = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            items.add(new OrderLineItem(i, product, amount + i, i));
            orderLineItemDAO.create(new OrderLineItem(i, product, amount + i, i));
        }
        assertEquals(items.size(),orderLineItemDAO.getAll().size());
    }
}
