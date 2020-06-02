package com.epam.dao;

import java.util.List;

public interface DAO<T, K> {
    T create(T object) throws DaoException;

    T readById(int id) throws DaoException;

    T readByKey(K key) throws DaoException;

    boolean update(T object) throws DaoException;

    boolean delete(T object) throws DaoException;

    List<T> getAll();

    boolean deleteAll() throws DaoException;
}
