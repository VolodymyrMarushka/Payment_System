package com.epam.services.impl;


import com.epam.dao.DAO;
import com.epam.dao.SupplierDaoH2;
import com.epam.domain.Supplier;
import com.epam.exception.SupplierServiceException;
import com.epam.services.CRUDService;
import org.apache.log4j.Logger;

public class SupplierServiceImpl  implements CRUDService<Supplier> {
    private static Logger logger = Logger.getLogger(SupplierServiceImpl.class);
    private DAO<Supplier,String> supplierDAO = new SupplierDaoH2();

    public Supplier save(Supplier someSupplier)  throws SupplierServiceException{

        if (someSupplier==null){
            throw new SupplierServiceException("There is no supplier!!! Add a supplier to save.");
        }

        if (someSupplier.getCompanyName()== null){
            throw new SupplierServiceException("Company name cannot be empty");
        }

        Supplier supplier = supplierDAO.create(someSupplier);

        if (supplier== null){
            throw new SupplierServiceException("Problem with saving supplier");
        }
        return supplier;
    }

    @Override
    public Supplier findById(int id) throws SupplierServiceException {

        if (id <= 0) {
            throw new SupplierServiceException("Category id cannot be 0 or negative!");
        }

        Supplier supplier = supplierDAO.readById(id);

        if (supplier == null){
            throw new SupplierServiceException("There is not category with the current identifier");
        }
        return supplier;
    }

    @Override
    public Supplier findByKey(String key) {
        if (key == null){
            throw new SupplierServiceException("Supplier namecan not be empty");
        }

        Supplier returned = supplierDAO.readByKey(key);

        if (returned == null ){
            throw new SupplierServiceException("There is not supplier with current name!");
        }

        return returned;
    }

    @Override
    public boolean update(int id, Supplier supplier) {

        if (id<= 0){
            throw new SupplierServiceException("Supplier id cannot be 0 or negative!");
        }

        if (supplier == null ){
            throw new SupplierServiceException("There is no supplier");
        }

        if (supplier.getCompanyName()== null ){
            throw new SupplierServiceException("Company name cannot be empty");
        }

        Supplier returned = supplierDAO.readById(id);

        if (returned == null ){
            throw new SupplierServiceException("There is not supplier with current identifier");
        }
        return supplierDAO.update(returned);
    }

    @Override
    public boolean deleteById(int id) {

        if (id<= 0 ){
            throw new SupplierServiceException("Supplier id cannot be 0 or negative!");
        }

        Supplier returned = supplierDAO.readById(id);

        if (returned == null ){
            throw new SupplierServiceException("There is not supplier with current id");
        }
        return supplierDAO.delete(returned);
    }
}
