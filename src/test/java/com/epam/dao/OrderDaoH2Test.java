package com.epam.dao;

import com.epam.domain.Order;
import com.epam.domain.OrderLineItem;
import com.epam.domain.OrderStatus;
import com.epam.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class OrderDaoH2Test {
//todo  fix a named package conflict
    private DAO<com.epam.domain.Order, String> orderDAO;
    private String address;
    private LocalDateTime ordered;
    private LocalDateTime shipped;
    private LocalDateTime delivered;
    private OrderStatus ordersStatus;
    // todo fix problem with Set<OrderLineItem>
    //private Set<OrderLineItem> items;
    private String updateAddress;
    private OrderStatus updatedOrderStatus;

    @BeforeEach
    public void init() {
        orderDAO = new OrderDaoH2();
        address = "Some address";
        ordered = LocalDateTime.of(2020,05,20,12,15);
        shipped = ordered.plusHours(2);
        delivered = shipped.plusHours(1);
        ordersStatus = OrderStatus.NEW;
        //items.add(new OrderLineItem(1,new Product(),3,2));
    }

    @Test
    @org.junit.jupiter.api.Order(0)
    public void DELETE_ALL() {
        boolean isDeleted = orderDAO.deleteAll();
        assertEquals(true, isDeleted);
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    public void CREATE_NONE_EXIST_ORDER() {
        Order order = new Order(0, ordersStatus, address, /*items,*/ ordered, shipped, delivered);
        Order returned = orderDAO.create(order);
        assertNotEquals(0, returned.getId());
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    public void CREATE_EXIST_ORDER() {
        Order order = new Order(0, ordersStatus, address, /*items,*/ ordered, shipped, delivered);
        assertThrows(DaoException.class, () -> {
            orderDAO.create(order);
        });
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    public void READ_BY_EXIST_ID() {
        int id = 1;
        Order returned = orderDAO.readById(id);
        assertEquals(id, returned.getId());
        assertEquals(address, returned.getAddress());
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    public void READ_BY_NONE_EXIST_ID() {
        int id = 0;
        assertThrows(DaoException.class, () -> {
            orderDAO.readById(id);
        });
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    public void READ_BY_EXIST_KEY() {
        Order order = orderDAO.readByKey(address);
        assertEquals(address, order.getAddress());
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    public void READ_BY_NONE_EXIST_KEY() {
        String str = "None exist key";
        assertThrows(DaoException.class, () -> {
            orderDAO.readByKey(str);
        });
    }

    @Test
    @org.junit.jupiter.api.Order(7)
    public void UPDATE_EXIST_OBJECT() {
        Order order = orderDAO.readByKey(address);
        order.setStatus(OrderStatus.SHIPPED);

        orderDAO.update(order);

        Order returned = orderDAO.readByKey(address);
        updatedOrderStatus = returned.getStatus();
        assertNotNull(returned);
        assertEquals(order.getStatus(), returned.getStatus());
    }

    @Test
    @org.junit.jupiter.api.Order(8)
    public void UPDATE_NONE_EXIST_OBJECT() {
        Order order = new Order(0, ordersStatus, address, /*items,*/ ordered, shipped, delivered);
        boolean isUpdated = orderDAO.update(order);
        assertEquals(false, isUpdated);
    }

    @Test
    @org.junit.jupiter.api.Order(9)
    public void DELETE_EXIST_OBJECT() {
        Order order = new Order(1, updatedOrderStatus, address, /*items,*/ ordered, shipped, delivered);
        assertEquals(true, orderDAO.delete(order));
    }

    @Test
    @org.junit.jupiter.api.Order(10)
    public void DELETE_NONE_EXIST_OBJECT() {
        Order order = new Order();
        assertEquals(false, orderDAO.delete(order));
    }

    @Test
    @org.junit.jupiter.api.Order(11)
    public void GET_ALL() {
        DELETE_ALL();
        List<Order> orders = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            orders.add(new Order(i, ordersStatus, address + i, /*items,*/ ordered.plusHours(i), shipped.plusHours(i + 1)
                    , delivered.plusHours(i + 2)));
            orderDAO.create(new Order(i, ordersStatus, address + i, /*items,*/ ordered.plusHours(i), shipped.plusHours(i + 1)
                    , delivered.plusHours(i + 2)));
        }
        assertEquals(orders, orderDAO.getAll());
    }

}
