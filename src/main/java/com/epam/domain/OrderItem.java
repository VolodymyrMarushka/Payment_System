package com.epam.domain;

import com.epam.dao.Identificator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem implements Identificator {

    private int id;
    private Product product;
    private int orderId;

}

/*
* product price
*
*
* */
