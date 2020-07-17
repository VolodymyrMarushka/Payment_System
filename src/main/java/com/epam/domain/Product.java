package com.epam.domain;

import com.epam.dao.Identificator;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Product implements Identificator {
    @Setter
    private int id;
    @Setter
    private String name;
    @Setter
    private BigDecimal price;
    @Setter
    private LocalDateTime produced;
    @Setter
    private LocalDateTime expiration;
    @Setter
    private Supplier supplier;
    @Setter
    private Category category;

}
