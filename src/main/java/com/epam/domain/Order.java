package com.epam.domain;

import com.epam.dao.Identificator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Identificator {

    private int id;
    private OrderStatus status;
    private String address;
   // private Set<OrderLineItem> items = new LinkedHashSet<>();
    private LocalDateTime ordered;
    private LocalDateTime shipped;
    private LocalDateTime delivered;

}
