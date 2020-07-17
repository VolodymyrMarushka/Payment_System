package com.epam.dao;

import com.epam.domain.Supplier;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SupplierDaoH2Test {

    private DAO<Supplier, String> supplierDAO;
    private String companyName;
    private String updateCompanyName;

    @BeforeEach
    public void init() {
        supplierDAO = new SupplierDaoH2();
        companyName = "Test Supplier";
    }

    @Test
    @Order(0)
    public void DELETE_ALL() {
        boolean isDeleted = supplierDAO.deleteAll();
        assertEquals(true, isDeleted);
    }

    @Test
    @Order(1)
    public void CREATE_NONE_EXIST_SUPPLIER() {
        Supplier supplier = new Supplier(0, companyName);
        Supplier returned = supplierDAO.create(supplier);
        assertNotEquals(0, returned.getId());
        assertEquals(companyName, returned.getCompanyName());
    }

    @Test
    @Order(2)
    public void CREATE_EXIST_SUPPLIER() {
        Supplier supplier = new Supplier(1, companyName);
        assertThrows(DaoException.class, () -> {
            supplierDAO.create(supplier);
        });
    }

    @Test
    @Order(3)
    public void READ_BY_EXIST_ID() {
        int id = 1;
        Supplier returned = supplierDAO.readById(id);
        assertEquals(id, returned.getId());
        assertEquals(companyName, returned.getCompanyName());
    }

    @Test
    @Order(4)
    public void READ_BY_NONE_EXIST_ID() {
        int id = 0;
        assertThrows(DaoException.class, () -> {
            supplierDAO.readById(id);
        });
    }

    @Test
    @Order(5)
    public void READ_BY_EXIST_KEY() {
        Supplier returned = supplierDAO.readByKey(companyName);
        assertEquals(companyName, returned.getCompanyName());
    }

    @Test
    @Order(6)
    public void READ_BY_NONE_EXIST_KEY() {
        String str = "NoneExistKey";
        assertThrows(DaoException.class, () -> {
            supplierDAO.readByKey(str);
        });
    }

    @Test
    @Order(7)
    public void UPDATE_EXIST_OBJECT() {
        Supplier supplier = supplierDAO.readByKey(companyName);
        supplier.setCompanyName("UpdateTest");

        supplierDAO.update(supplier);

        Supplier returned = supplierDAO.readByKey("UpdateTest");
        updateCompanyName = returned.getCompanyName();
        assertNotNull(returned);
        assertEquals(supplier.getCompanyName(), returned.getCompanyName());
    }

    @Test
    @Order(8)
    public void UPDATE_NONE_EXIST_OBJECT() {
        Supplier supplier = new Supplier(0, companyName);
        boolean isUpdated = supplierDAO.update(supplier);
        assertEquals(false, isUpdated);
    }

    @Test
    @Order(9)
    public void DELETE_EXIST_OBJECT() {
        Supplier supplier = new Supplier(1, updateCompanyName);
        assertEquals(true, supplierDAO.delete(supplier));
    }

    @Test
    @Order(10)
    public void DELETE_NONE_EXIST_OBJECT() {
        Supplier supplier = new Supplier(0, "None Exist Name");
        assertEquals(false, supplierDAO.delete(supplier));
    }

    @Test
    @Order(11)
    public void getAll() {
        DELETE_ALL();
        List<Supplier> suppliers = new ArrayList<>();
        String supplier = "Supplier ";
        for (int i = 1; i < 5; i++) {
            suppliers.add(new Supplier(i,supplier+i));
            supplierDAO.create(new Supplier(i,supplier+i));
        }
        assertEquals(suppliers,supplierDAO.getAll());
    }
}
