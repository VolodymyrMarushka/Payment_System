package com.epam.dao;

import com.epam.domain.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDaoH2 extends AbstractDao<Supplier, String> {

    @Override
    public String getCreateQuery() {
        return "insert into `supplier` (companyName) values (?)";
    }

    @Override
    protected void setStatementForCreate(PreparedStatement pstm, Supplier someObject) {
        try {
            pstm.setString(1, someObject.getCompanyName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected List<Supplier> parseResultSet(ResultSet rs) {

        List<Supplier> suppliers = new ArrayList<>();
        try {
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt(1));
                supplier.setCompanyName(rs.getString(2));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
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
        return "select * from `supplier` where id = ?";
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
        return "select * from `supplier` where `companyName` = ?";
    }

    @Override
    protected void setStatementForUpdate(PreparedStatement pstm, Supplier object) {
        try {
            pstm.setString(1, object.getCompanyName());
            pstm.setInt(2, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getUpdateQuery() {
        return "Update `supplier` set companyName = ? where id = ?";
    }

    @Override
    protected void setStatementForDelete(PreparedStatement pstm, Supplier object) {
        try {
            pstm.setInt(1, object.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String getDeleteQuery() {
        return "delete from `supplier` where id = ?";
    }

    @Override
    protected String getQueryForGetAll() {
        return "Select * from supplier ";
    }

    @Override
    protected String getDeleteAllQuery() {
        return "Delete from `supplier` ";
    }

    @Override
    protected String getTableName() {
        return "Supplier";
    }


}
