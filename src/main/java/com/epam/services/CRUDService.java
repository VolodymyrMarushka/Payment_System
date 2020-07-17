package com.epam.services;

import com.epam.domain.Category;

public interface CRUDService<T> {
    T save(T t);

    T findById(int id);

    T findByKey(String key);

    boolean update(int id, T object);

    boolean deleteById(int id);
}
