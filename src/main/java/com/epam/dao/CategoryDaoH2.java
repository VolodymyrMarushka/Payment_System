package com.epam.dao;


import com.epam.domain.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.constant.daoQueries.CategoryDAOQueries.*;

public class CategoryDaoH2 extends AbstractDao<Category, String> {

    @Override
    protected String getCreateQuery() {
        return INSERT;
    }

    @Override
    protected void setStatementForCreate(PreparedStatement pstm, Category someObject) {
        try {
            pstm.setString(1, someObject.getName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<Category> parseResultSet(ResultSet rs) {
        List<Category> categories = new ArrayList<>();

        try {
            while (rs.next()) {
                Category category = new Category();
                category.setName(rs.getString(2));
                category.setId(rs.getInt(1));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    protected void setStatementForId(PreparedStatement pstm, int id) {
        try {
            pstm.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getSelectByIdQuery() {
        return SELECT_BY_ID;
    }

    @Override
    protected void setStatementForKey(PreparedStatement pstm, String key) {
        try {
            pstm.setString(1, key);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getSelectByKeyQuery() {
        return SELECT_BY_KEY;
    }

    @Override
    protected void setStatementForUpdate(PreparedStatement pstm, Category object) {
        try {
            pstm.setString(1, object.getName());
            pstm.setInt(2, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE;
    }

    @Override
    protected void setStatementForDelete(PreparedStatement pstm, Category object) {
        try {
            pstm.setInt(1, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_BY_ID;
    }

    @Override
    protected String getQueryForGetAll() {
        return SELECT_ALL;
    }

    @Override
    protected String getDeleteAllQuery() {
        return DELETE_ALL;
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

}
