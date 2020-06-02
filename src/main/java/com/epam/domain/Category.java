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
public class Category implements Identificator {

    private int id;
    private String name;

    public Category(String name) {
        this.name = name;
    }
}
